/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import helper.MealCategoryDBHelper;
import helper.MealDBHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Meal;
import model.MealCategory;
import model.MyNotify;
import view.Navigator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EditMealController implements Initializable {
    private StringProperty imgName = new SimpleStringProperty();
    private String fileName;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnBack;

    @FXML
    private ChoiceBox<String> cbCategory;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnUpload;
    @FXML
    private Label lbImage;

    @FXML
    private ImageView imgUpload;

    @FXML
    private GridPane gridMain;
    private Meal meal;

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMealManager();
    }

    @FXML
    void clickSubmit(ActionEvent event) throws SQLException, IOException {
        if (!validateInfo()) {
            String stringError = "";
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError += entry.getValue() + "\n";
            }
            MyNotify.MyNotifyAlertError(stringError);
        } else {
            boolean result = MealDBHelper.updateMeal(txtName.getText(), Float.parseFloat(txtPrice.getText()), fileName,  MealsManagerController.mealCategoryNameId.get(cbCategory.getValue()), this.meal.getId() );
            if (result) {
                Navigator.getInstance().goToMealManager();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insert Failed");
                alert.setContentText("The meal has not been added yet");
                alert.show();
            }
        }
    }

    @FXML
    void clickUpload(ActionEvent event) throws IOException {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choose File Dialog");
        Stage stage = (Stage) gridMain.getScene().getWindow();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Img File", "*.png", "*.jpeg", "*.jpg"));
        File file = filechooser.showOpenDialog(stage);
        lbImage.setText(file.getName());

        int i = 0, count, n;
        String from, to, s;
        to = "src/img/images/";
        String localUrl = file.toURI().toString();
        count = localUrl.lastIndexOf("/");
        copyFile(localUrl.substring(6), to + localUrl.substring(count + 1));
        imgUpload.setImage(new Image(file.toURI().toString()));
        imgName.setValue(file.getName());
        this.fileName = file.getName();
    }
    void copyFile(String from, String to) throws IOException {
        Path fromFile = Paths.get(from);
        Path toFile = Paths.get(to);
        try {
            Files.copy(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private HashMap<String, String> error = new HashMap<String, String>();
    boolean validateInfo(){
        error.clear();
        if (txtName.getText().isEmpty()){
            error.put("Username", "Please enter meal name");
        }
        if (cbCategory.getValue() == null){
            error.put("Category", "Please choose category");
        }
        if (txtPrice.getText().isEmpty()){
            error.put("Price", "Please enter price");
        }
        if (imgName.getValue() == null){
            error.put("Image", "Please choose image");
        }
        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<MealCategory> listMeal =  MealCategoryDBHelper.getAllMealCategory();
        ObservableList<String> listMealStr = FXCollections.observableArrayList();
        for (MealCategory mealCategory: listMeal
             ) {
            listMealStr.add(mealCategory.getMealCategory());
        }
        cbCategory.setItems(listMealStr);
    }

    public void loadData(Meal meal){
        Image img = new Image(getClass().getResourceAsStream("/img/images/" + meal.getImage()));
        txtName.setText(meal.getName());
        cbCategory.setValue(meal.getCategory());
        txtPrice.setText(meal.getPrice().toString());
        imgUpload.setImage(img);
        this.meal = meal;
    }
}
