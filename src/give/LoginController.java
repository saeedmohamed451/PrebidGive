/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give;

import give.model.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public class LoginController {
    @FXML private TextField username;
    @FXML private TextField password;

    public void loginButtonPressed(ActionEvent event) throws IOException {
        User user = new User(username.getText(), password.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/fxml_home.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        // Set the user for remainder of the session
        HomeController homeController = loader.getController();
        homeController.setUser(user);
        
        // Get stage and transfer to home scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
        
    }
}
