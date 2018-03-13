package give.core.controller;

import give.base.controller.BaseController;
import give.core.model.CategoryModel;
import give.core.services.CharityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserCharityController extends BaseController implements Initializable {

    @FXML private FlowPane flowPane;
    @FXML private FlowPane charityPane;

    CharityService charityService = new CharityService();

    ArrayList<CategoryModel> allCategories = null;


    ArrayList<FlowPane> allSubPanes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        charityPane.setMinHeight(0);

        allCategories = this.charityService.getCategories();

        for(int i = 0; i < allCategories.size(); i++) {
            CategoryModel category = allCategories.get(i);

            ;
        }
    }

    public void onCloseBtnPressed(ActionEvent event) throws IOException {
        // Back to Home View
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BaseController.getResourceURL("view/fxml_home.fxml"));
        Scene homeScene = new Scene(loader.load());

        // Get stage and transfer to home scene
        this.showWindow(event, homeScene);
    }
}
