/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.controller;

import give.Give;
import give.base.controller.BaseController;
import give.core.model.UserModel;
import java.awt.Desktop.Action;
import java.awt.Paint;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


/**
 * FXML Controller class
 *
 * @author noahwebb
 */
public class HomeController extends BaseController implements Initializable {

    @FXML
    private Label lblUserName;
    private String komanda;
    public VBox charityCheckList;
    public Button firstField, secondField, thirdField, fourthField;
    public boolean first = false,second = false ,third = false,fourth = false;
    @FXML public Pane menuBar;
    @FXML public double xOffset, yOffset;
    @FXML public Stage stage;
    
    
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected = 4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUserName.setText(Give.LOGIN_USER.getUserName());

        //submitButton.setDisable(true);
        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));

            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));

            }
        });
        
        

    }

    public void createField(String nm, String desc) {

        AnchorPane checkPane = new AnchorPane();
        checkPane.setId("checkPane");
        CheckBox check = new CheckBox();
        check.setId("checkBox");
        check.setLayoutX(20);
        check.setLayoutY(30);
        check.setText(nm);
        check.setOnAction(checkBoxEvent);

        Label name = new Label(nm);
        name.setId("nameLabel");
        name.setLayoutX(60);
        name.setLayoutY(10);

        Label description = new Label(desc);
        description.setId("descriptionLabel");
        description.setLayoutX(60);
        description.setLayoutY(35);

        checkPane.getChildren().addAll(check, description, name);
        charityCheckList.getChildren().add(checkPane);
        configureCheckBox(check);

    }

    private void configureCheckBox(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }

        });

    }

    public void charPressed(ActionEvent event) throws SQLException {

        charityCheckList.setVisible(true);
        Button button = (Button) event.getSource();
        String[] source = button.getText().split("|", 3);
        charityCheckList.getChildren().clear();
        Label charityName = new Label(source[2]);
        
        String categoryLogoImageURL = getAppPath() + "\\src\\give\\view\\images\\" + charityName.getText().toLowerCase()+ ".png";
        Image image = new Image("file:///" + categoryLogoImageURL);
        ImageView listLogo = new ImageView(image);
        listLogo.setFitWidth(50);
        listLogo.setFitHeight(50);
        Button b = new Button();
        b.setId("charityCategoryLogoImg");
        b.setGraphic(listLogo);
       
        HBox nameLogoBox = new HBox();
        nameLogoBox.minWidth(900);
        nameLogoBox.prefWidth(900);
        nameLogoBox.minHeight(70);
        nameLogoBox.setAlignment(Pos.CENTER_LEFT);
        nameLogoBox.setSpacing(25);
        charityCheckList.getChildren().add(nameLogoBox);
        nameLogoBox.setId("nameLogoBox");
        nameLogoBox.getChildren().add(b);
        
        charityName.setId("charityName"); // charity name
        nameLogoBox.getChildren().add(charityName);
        charityCheckList.setStyle("-fx-padding: 20 30 20 30;\n"
                + "-fx-background-insets: 20 30 20 30;\n"
                + "-fx-border-insets: 20 30 20 30;"
                + "-fx-pref-width: 900;");

        String queryString = "SELECT * FROM prebid.charity WHERE category=" + "\"" + source[0] + "\"";
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery(queryString);
        try {
            while (rset.next()) {
                createField(rset.getString(2), rset.getString(4));
            }

        } catch (Exception ex) {
            System.out.println("Exception" + ex.getMessage());
        }

    }

    EventHandler checkBoxEvent = (EventHandler<ActionEvent>) (ActionEvent event) -> {
        CheckBox box = (CheckBox) event.getSource();
        String strImgPath = "";
        
        if (box.isSelected()) {

            try {

                String queryString = "SELECT logo FROM prebid.charity where name=\"" + box.getText() + "\";";
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(queryString);

                while (rset.next()) {

                    strImgPath = getAppPath() + "images\\charity\\" + rset.getString(1);
                   

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

            

                Image image = new Image("file:///" + strImgPath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setId("charityPic");
                
                
                if(first == false){
                    firstField.setGraphic(imageView);
                    firstField.setText(box.getText());
                    firstField.setTextFill(Color.TRANSPARENT);
                    firstField.setStyle("-fx-font-size:1; -fx-background-insets:0 4 0 4; -fx-text-fill:transparent; -fx-padding:0 0 0 10");
                    firstField.setMinWidth(230);
                    first = true;
                    
                
                }else if(second == false){
                
                    secondField.setGraphic(imageView);
                    secondField.setText(box.getText());
                    secondField.setTextFill(Color.TRANSPARENT);
                    second = true;
                    
                }else if(third == false){
                
                    thirdField.setGraphic(imageView);
                    thirdField.setText(box.getText());
                    thirdField.setTextFill(Color.TRANSPARENT);
                    third = true;
                    
                }else if(fourth == false){
                
                    fourthField.setGraphic(imageView);
                    fourthField.setText(box.getText());
                    fourthField.setTextFill(Color.TRANSPARENT);
                    fourth = true;
                    
                }
                
                


        } else {

            if(box.getText().equals(firstField.getText())){
                first = false;
                firstField.setGraphic(null);
                firstField.setText("SELECT A GREAT CHARITY FROM BELOW");
                firstField.setTextFill(Color.rgb(255, 255, 255, 0.6));
                firstField.setWrapText(true);
                firstField.setStyle("-fx-font-size:18; -fx-text-fill: rgba(255, 255, 255, 0.6);");
            }else if(box.getText().equals(secondField.getText())){
                second = false;
                secondField.setGraphic(null);
                secondField.setText("AND ANOTHER");
                secondField.setTextFill(Color.rgb(255, 255, 255, 0.6));
            }else if(box.getText().equals(thirdField.getText())){
                third = false;
                thirdField.setGraphic(null);
                thirdField.setText("AND ANOTHER");
                thirdField.setTextFill(Color.rgb(255, 255, 255, 0.6));
            }else if(box.getText().equals(fourthField.getText())){
                fourth = false;
                fourthField.setGraphic(null);
                fourthField.setText("AND ANOTHER");
                fourthField.setTextFill(Color.rgb(255, 255, 255, 0.6));
            }

        }

    };
    
    
    
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
