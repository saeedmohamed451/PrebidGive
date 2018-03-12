/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give;

import give.base.controller.BaseController;
import give.base.db.DBConfig;
import give.core.db.GiveDBHelper;
import give.core.model.UserModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author noahwebb
 */
public class Give extends Application {
    
    public static GiveDBHelper G_DBHelper = null;
    public static String RESOURCE_PATH = null;
    public static UserModel LOGIN_USER = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        RESOURCE_PATH = getClass().getResource("").toString();
        
        Parent root = FXMLLoader.load(BaseController.getResourceURL("view/fxml_login.fxml"));
        
        Scene scene = new Scene(root);
        stage.resizableProperty().setValue(Boolean.FALSE);
        
        stage.setTitle("Give");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        G_DBHelper = new GiveDBHelper(new DBConfig());
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
              G_DBHelper.closeDB();
            }
        });
                
        launch(args);
    }
    
    
    
    
}
