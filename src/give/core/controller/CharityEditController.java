/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.controller;

import give.base.controller.BaseController;
import give.core.model.CharityModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.Timer;

import give.core.services.CharityService;
import give.util.FileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class CharityEditController extends BaseController implements Initializable {
    
    @FXML private TextField txtName;
    @FXML private TextField txtSite;
    @FXML private TextField txtCategory;
    @FXML private TextField txtAmount;
    @FXML private TextField txtNumber;
    @FXML private TextField txtMetric;
    @FXML private TextArea txtBio;
    @FXML private ImageView imgLogo;

    @FXML private ToggleButton btnUpload;
    @FXML private ToggleButton btnSave;

    
    // Data Variable
    String m_strCurImage = "";
    CharityModel m_curCharity;

    CharityService m_charityService = new CharityService();

    public void setCurrentCharity(CharityModel charity) {
        this.m_curCharity = charity;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initControls(this.m_curCharity);
    }
    
    public void onLogoUploadBtnPressed(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Logo File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(getWindowBy(event, true));
        if (file != null) {
            String ext = FileUtil.getFileExt(file.getAbsolutePath());

            if(FileUtil.isJPG(file.getAbsolutePath()) || FileUtil.isPNG(file.getAbsolutePath())) {
                // Generate File name
                long lTime = System.currentTimeMillis() / 1000;

                String strImgPath = getAppPath() + "images/" + lTime + "." + ext;
                File destFile = new File(strImgPath);

                // Copy File
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Show Image
                m_strCurImage = strImgPath;
                imgLogo.setImage(new Image("file:///" + m_strCurImage));
            }
            else {
                this.showAlert("Invalid Image File Format.");
            }
        }
    }
    
    public void onSaveBtnPressed(ActionEvent event) throws IOException {
        // Check Name
        String strName = txtName.getText();
        if(strName.trim().length() == 0) {
            this.showAlert("Please Input the Name.");
            txtName.requestFocus();
            return;
        }

        // Check BIO
        String strBIO = txtBio.getText();
        if(strBIO.trim().length() == 0) {
            this.showAlert("Please Input the BIO.");
            txtBio.requestFocus();
            return;
        }

        // Check Web Site
        String strSite = txtSite.getText();
        if(strName.trim().length() == 0) {
            this.showAlert("Please Input the Website address.");
            txtSite.requestFocus();
            return;
        }

        // Check Category
        String strCategory = txtCategory.getText();
        if(strName.trim().length() == 0) {
            this.showAlert("Please Input the Category name.");
            txtCategory.requestFocus();
            return;
        }

        // Check Metric
        String strMetric = txtMetric.getText();
        if(strName.trim().length() == 0) {
            this.showAlert("Please Input the Metric.");
            txtMetric.requestFocus();
            return;
        }

        // Check Number
        Integer nNumber = 0;
        try {
            nNumber = Integer.valueOf(txtNumber.getText());
        }
        catch(Exception e) {
            nNumber = -1;
        }
        if(nNumber < 0) {
            this.showAlert("Please Input the Number correctly.");
            txtNumber.requestFocus();
            return;
        }

        // Check Amount
        Double dblAmount = 0.0;
        try {
            dblAmount = Double.valueOf(txtAmount.getText());
        }
        catch(Exception e) {
            dblAmount = -1.0;
        }
        if(dblAmount < 0) {
            this.showAlert("Please Input the Amount correctly.");
            txtAmount.requestFocus();
            return;
        }

        // Check Image
        if(m_strCurImage.length() == 0) {
            this.showAlert("Please update an Image.");
            return;
        }

        // Create New Charity Object to Save
        CharityModel newCharity = new CharityModel();
        newCharity.setName(strName);
        newCharity.setBIO(strBIO);
        newCharity.setImage(m_strCurImage);
        newCharity.setSite(strSite);
        newCharity.setCategory(strCategory);
        newCharity.setMetric(strMetric);
        newCharity.setAmount(dblAmount);
        newCharity.setNumber(nNumber);

        if(m_curCharity == null) {
            if(m_charityService.addNewCharity(newCharity)) {
                if(this.showConfirm("Would you like to add more?")) {
                    // Initialize
                    initControls(null);
                }
                else {
                    onCloseBtnPressed(event);
                }
            }
            else {
                this.showAlert("Failed to add this charity.\nPlease try again.");
            }
        }
        else {
            newCharity.setID(m_curCharity.getID());
            if(!m_charityService.updateCharity(newCharity)) {
                this.showAlert("Failed to update this charity.\nPlease try again.");
            }
        }
    }

    public void onCloseBtnPressed(ActionEvent event) throws IOException {
        // Back to List View
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BaseController.getResourceURL("view/fxml_charity_list.fxml"));
        Scene homeScene = new Scene(loader.load());

        // Get stage and transfer to home scene
        this.showWindow(event, homeScene);
    }

    private void initControls(CharityModel charity) {
        this.m_curCharity = charity;

        if(this.m_curCharity == null) {
            txtName.setText("");
            txtSite.setText("");
            txtCategory.setText("");
            txtBio.setText("");
            txtMetric.setText("");
            txtNumber.setText("0");
            txtAmount.setText("0.0");

            m_strCurImage = "";
            imgLogo.setImage(null);

            btnSave.setText("Add Charity");
        }
        else {
            txtName.setText(m_curCharity.getName());
            txtSite.setText(m_curCharity.getSite());
            txtCategory.setText(m_curCharity.getCategory());
            txtBio.setText(m_curCharity.getBIO());
            txtMetric.setText(m_curCharity.getMetric());
            txtNumber.setText(String.valueOf(m_curCharity.getNumber()));
            txtAmount.setText(String.valueOf(m_curCharity.getName()));

            m_strCurImage = m_curCharity.getImage();
            imgLogo.setImage(new Image(m_strCurImage));

            btnSave.setText("Update Charity");
        }
    }
}
