/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.controller;

import give.Give;
import give.base.controller.BaseController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author noahwebb
 */
public class HomeController extends BaseController implements Initializable {
    @FXML
    private Label lblUserName;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUserName.setText(Give.LOGIN_USER.getUserName());
    }

    public void giveButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getResourceURL("view/fxml_give.fxml"));

        Parent giveParent = loader.load();
        Scene giveScene = new Scene(giveParent);
        // Get stage and transfer to give scene
        Stage window = BaseController.getWindowBy(event, false);
        window.hide();
        GiveController controller = loader.getController();
        controller.mainStage = window;

        Stage newWindow = new Stage();
        newWindow.setAlwaysOnTop(true);
        newWindow.setScene(giveScene);
     
        newWindow.show();
        addToSystemTray(window);
    }

    private void addToSystemTray(Stage mainStage) {
        if (!SystemTray.isSupported()) {
            System.err.println("System tray is not supported!");
            return;
        }
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("tray_icon.png");
        TrayIcon trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (TrayIcon icon : tray.getTrayIcons()) {
                    tray.remove(icon);
                }
                Platform.runLater(mainStage::show);
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("Cannot add system tray!");
            e.printStackTrace();
        }
    }

    public void accountButtonPressed(ActionEvent event) throws IOException {
        Parent accountParent = FXMLLoader.load(BaseController.getResourceURL("view/fxml_account.fxml"));
        Scene accountScene = new Scene(accountParent);
       
        // Get stage and transfer to give scene
        this.showWindow(event, accountScene);
    }

    public void charityButtonPressed(ActionEvent event) throws IOException {
        Parent charityParent = FXMLLoader.load(BaseController.getResourceURL("view/fxml_charity_list.fxml"));
        Scene charityScene = new Scene(charityParent);
        
        // Get stage and transfer to charity scene
        this.showWindow(event, charityScene);
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
}
