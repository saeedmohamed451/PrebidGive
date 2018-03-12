/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.controller;

import give.base.controller.BaseController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;


/**
 *
 * @author Administrator
 */
public class CharityListController extends BaseController implements Initializable {

    @FXML private Hyperlink linkNew;
    @FXML private Hyperlink linkEdit;
    @FXML private Hyperlink linkDelete;

    @FXML private ImageView imgLogo;
    @FXML private TableView tableCharities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void onCloseBtnPressed(ActionEvent event) throws IOException {
        // Back to List View
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BaseController.getResourceURL("view/fxml_home.fxml"));
        Scene homeScene = new Scene(loader.load());

        // Get stage and transfer to home scene
        this.showWindow(event, homeScene);
    }

    public void onLinkNewPressed(ActionEvent event) throws IOException {
        // Back to List View
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BaseController.getResourceURL("view/fxml_charity_edit.fxml"));
        Scene homeScene = new Scene(loader.load());

        // Get stage and transfer to home scene
        this.showWindow(event, homeScene);
    }

    public void onLinkEditPressed(ActionEvent event) throws IOException {
        ;
    }

    public void onLinkDeletePressed(ActionEvent event) throws IOException {
        ;
    }
}
