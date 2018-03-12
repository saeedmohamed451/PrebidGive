/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.controller;

import give.Give;
import give.base.controller.BaseController;
import give.core.model.UserModel;
import give.core.services.UserService;
import give.util.ValidationUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Administrator
 */
public class UserController extends BaseController implements Initializable {
    
    @FXML private Label lblUserName;
    
    @FXML private TextField txtUserName;
    @FXML private TextField txtUserEmail;
    
    @FXML private TextField txtCurPwd;
    @FXML private TextField txtPassword;
    @FXML private TextField txtConfPwd;
    
    String m_strCurLocation = "";
    UserService m_userService = new UserService();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.m_strCurLocation = location.toString();
        
        // If Edit User
        if(this.m_strCurLocation.contains("fxml_account")) {
            lblUserName.setText(Give.LOGIN_USER.getUserName());
            txtUserEmail.setText(Give.LOGIN_USER.getUserEmail());
        }
    }
 
    public void onSignupBtnPressed(ActionEvent event) throws IOException {
        String strUserName = txtUserName.getText();
        String strUserEmail= txtUserEmail.getText();
        String strPassword = txtPassword.getText();
        String strConfPwd  = txtConfPwd.getText();
        
        // Check User Name
        if(strUserName.length() == 0) {
            this.showAlert("Please input full name.");
            txtUserName.requestFocus();
            return;
        }
        
        if(!ValidationUtil.isVaildName(strUserName)) {
            this.showAlert("Please input full name correctly.");
            txtUserName.requestFocus();
            return;
        }
        
        // Check User Email
        if(strUserEmail.length() == 0) {
           this.showAlert("Please Input Email.");
           txtUserEmail.requestFocus();
           
           return;
        }
        
        if(!ValidationUtil.isValidEmail(strUserEmail)) {
            this.showAlert("Please Input Email correctly.");
           txtUserEmail.requestFocus();
           
           return;
        }
        
        // Check User Password
        if(strPassword.length() == 0) {
           this.showAlert("Please Input Password.");
           txtUserEmail.requestFocus();
           
           return;
        }
       
        if(strPassword.length() < 8) {
           this.showAlert("The length of password is 8 at least.");
           txtUserEmail.requestFocus();
           
           return;
        }
        
        if(strPassword.compareTo(strConfPwd) != 0) {
           this.showAlert("Please confirm your password.");
           txtConfPwd.requestFocus();
           
           return;
        }
        
        // Create UserModel
        UserModel newUser = new UserModel(strUserName, strUserEmail, strPassword);
        
        // Check duplication
        if(m_userService.findUserByEmail(strUserEmail) != null) {
            this.showAlert("The email is already used.\nPlease input different Email.");
            txtUserEmail.requestFocus();
           
            return;
        }
        
        if(m_userService.registerUser(newUser)) {
            this.showPrompt("New user registered successfuly.\nPlease login to check that.");
                
            onCancelBtnPressed(event);
        }
        else {
            this.showAlert("Failed to register.\nPlease try again.");
            txtUserEmail.requestFocus();
        }
    }
    
    public void onUpdateBtnPressed(ActionEvent event) throws IOException {
        String strNewEmail = txtUserEmail.getText();
        String strCurPwd   = txtCurPwd.getText();
        String strNewPwd   = txtPassword.getText();
        String strConfPwd  = txtConfPwd.getText();
        
        if(strNewEmail.length() == 0 || !ValidationUtil.isValidEmail(strNewEmail)) {
            this.showAlert("Please Input Email correctly.");
            txtUserEmail.requestFocus();
           
            return;
        }
        
        if(strNewEmail.compareTo(Give.LOGIN_USER.getUserEmail()) != 0) {
            // Check Duplications
            UserModel foundUser = this.m_userService.findUserByEmail(strNewEmail);
            if(foundUser != null && foundUser.getUserEmail().compareTo(Give.LOGIN_USER.getUserEmail()) != 0) {
                this.showAlert("The email is already used.\nPlease input different Email.");
                txtUserEmail.requestFocus();

                return;
            }
        }
        
        // Check Current Password
        if(this.m_userService.authenticateUser(Give.LOGIN_USER.getUserEmail(), strCurPwd) == null) {
            this.showAlert("Your password is wrong.");
            txtUserEmail.requestFocus();

            return;
        }
        
        // Check New Password
        if(strNewPwd.length() < 8) {
           this.showAlert("The length of new password is 8 at least.");
           txtUserEmail.requestFocus();
           
           return;
        }
        
        if(strNewPwd.compareTo(strConfPwd) != 0) {
           this.showAlert("Please confirm your new password.");
           txtConfPwd.requestFocus();
           
           return;
        }
        
        // Update
        if(this.showConfirm("Would you like to update your account now?")) {
            UserModel uptUser = new UserModel(Give.LOGIN_USER.getUserName(), strNewEmail, strNewPwd);
            if(!this.m_userService.updateUser(uptUser)) {
                this.showAlert("Failed to update your account.\nPlease try again.");            
            }
            else {
                Give.LOGIN_USER.setUserEmail(strNewEmail);
                
                this.showPrompt("Your account has been updated successfully.");
                this.onCancelBtnPressed(event);
            }
        }
    }
    
    public void onCancelBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BaseController.getResourceURL(m_strCurLocation.contains("fxml_signup") ? "view/fxml_login.fxml" : "view/fxml_home.fxml"));
        Scene homeScene = new Scene(loader.load());
        
        // Get stage and transfer to home scene
        this.showWindow(event, homeScene);
    }
}
