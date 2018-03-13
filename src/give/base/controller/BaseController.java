/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.base.controller;

import give.Give;
import static give.Give.RESOURCE_PATH;
import give.core.db.GiveDBHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class BaseController {
    GiveDBHelper m_dbHelper = null;
    
    public BaseController (){
        this.m_dbHelper = Give.G_DBHelper;
    }

    public void onLinkLogoutPressed(ActionEvent event) throws IOException {
        if(this.showConfirm("Would you like to logout now?")) {
            Give.LOGIN_USER = null;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BaseController.getResourceURL("view/fxml_login.fxml"));
            Scene homeScene = new Scene(loader.load());

            // Get stage and transfer to home scene
            this.showWindow(event, homeScene);
        }
    }

    public static String getAppPath() {
        return System.getProperty("user.dir") + "/";
    }
    
    /**
     * 
     * @param resFile
     * @return 
     */
    public static URL getResourceURL(String resFile) {
        URL newURL = null;
        try {
            newURL = new URL(RESOURCE_PATH + resFile);
        }
        catch(Exception e) {
            
        }
        
        return newURL;
    }
    
    
    public static Stage getWindowBy(ActionEvent event, boolean bEnableResize) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(bEnableResize);

        return window;
    }
    
    public void showWindow(ActionEvent event, Scene scene) {
        // Get stage and transfer to home scene
        Stage window = BaseController.getWindowBy(event, false);
        window.setScene(scene);
        window.show();
    }
    
    public boolean showAlert(String strParamMessage) {
        Alert alert = new Alert(AlertType.WARNING, strParamMessage);        
        Optional<ButtonType> result = alert.showAndWait();
        
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
    
    public boolean showPrompt(String strParamMessage) {
        Alert alert = new Alert(AlertType.INFORMATION, strParamMessage);        
        Optional<ButtonType> result = alert.showAndWait();
        
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
    
    public boolean showConfirm(String strParamMessage) {
        Alert alert = new Alert(AlertType.CONFIRMATION, strParamMessage);
        Optional<ButtonType> result = alert.showAndWait();
        
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
}
