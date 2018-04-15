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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

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
    @FXML private TextField yourDonationField, firstTopDonationField, secondTopDonationField, thirdTopDonationField, firstDonationNameField, secondDonationNameField, thirdDonationNameField;
    @FXML private VBox accountSettingsPane;
    @FXML private HBox accountStatusPane;
    @FXML public Pane menuBar;
    @FXML public double xOffset, yOffset;
    @FXML public Stage stage;
    @FXML private RadioButton boxEmailChange, boxPasswordChange;

    
    public PieChart piechart;

    @FXML private Hyperlink linkStats;
    
    String m_strCurLocation = "";
    UserService m_userService = new UserService();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.m_strCurLocation = location.toString();
       
       
        
        Integer allTime = 0, education = 0, animals = 0, enivironment = 0, health = 0, refuges = 0, hunger = 0, water = 0, children = 0, povetry = 0, injustice = 0;
   
        try{
        String queryString = "SELECT * FROM prebid.user WHERE email=\""+UserModel.getUserEmail()+"\"";
         Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);
               while(rset.next()){
                 
                   
                   education = rset.getInt(10);
                   animals = rset.getInt(11);
                   enivironment = rset.getInt(12);
                   health = rset.getInt(13);
                   refuges = rset.getInt(14);
                   hunger = rset.getInt(15);
                   water = rset.getInt(16);
                   children = rset.getInt(17);
                   povetry = rset.getInt(18);
                   injustice = rset.getInt(19);
                   
                }

        }catch(Exception e){
            System.out.println("Exception" + e.getMessage());
        }
        allTime = education + animals + enivironment + health + refuges + hunger + water + children + povetry + injustice;
        yourDonationField.setText(allTime.toString());
        
        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Education", education),
                    new PieChart.Data("Animals", animals),
                    new PieChart.Data("Enivironment", enivironment),
                    new PieChart.Data("Health", health),
                    new PieChart.Data("Refuges", refuges),
                    new PieChart.Data("Hunger", hunger),
                    new PieChart.Data("Water", water),
                    new PieChart.Data("Children", children),
                    new PieChart.Data("Povetry", povetry),
                    new PieChart.Data("Injustice", injustice));

         
        piechart.setTitle("Your donations");
        piechart.setData(pieChartData);
        
//        SortedList <PieChart.Data>donacije = piechart.getData().sorted();
//        
//        
//        
//        Double first = donacije.get(9).getPieValue();
//        Double second = donacije.get(8).getPieValue();
//        Double third = donacije.get(7).getPieValue();
//        
//        firstTopDonationField.setText(first.toString());
//        secondTopDonationField.setText(second.toString());
//        thirdTopDonationField.setText(third.toString());
//        
//        firstDonationNameField.setText(donacije.get(9).getName());
//        secondDonationNameField.setText(donacije.get(8).getName());
//        thirdDonationNameField.setText(donacije.get(7).getName());
//        
       
        // If Edit User
        if(this.m_strCurLocation.contains("fxml_account")) {
            //lblUserName.setText(Give.LOGIN_USER.getUserName());
            //txtUserEmail.setText(Give.LOGIN_USER.getUserEmail());

            //linkStats.setVisible(Give.LOGIN_USER.getUserType() == UserModel.USER_TYPE_ADMIN);
        }
    }
    
    public void accountButtonPressed(ActionEvent event) throws IOException {
        Parent accountParent = FXMLLoader.load(BaseController.getResourceURL("view/fxml_account.fxml"));
        Scene accountScene = new Scene(accountParent);
       
        // Get stage and transfer to give scene
        this.showWindow(event, accountScene);
    }
    
    public void statsPressed(){
    
        accountSettingsPane.setVisible(false);
        accountStatusPane.setVisible(true);
        
    }
    
    public void settingsPressed(){
    
        accountSettingsPane.setVisible(true);
        accountStatusPane.setVisible(false);
        
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
        loader.setLocation(BaseController.getResourceURL(m_strCurLocation.contains("") ? "view/fxml_login.fxml" : "view/fxml_home.fxml"));
        Scene homeScene = new Scene(loader.load());
        homeScene.getStylesheets().add("give/view/login.css");
        
        // Get stage and transfer to home scene
        this.showWindow(event, homeScene);
    }
    
    public void menuBarPressed(MouseEvent event){
    
        stage = (Stage)menuBar.getScene().getWindow();
         xOffset = event.getSceneX();
         yOffset = event.getSceneY();
        
    }
    
    
    public void menuBarDragged(MouseEvent event){
        stage = (Stage)menuBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
        
        
    }
    
    
}
