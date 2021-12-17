package controller;

import helper.MealCategoryDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.MyNotify;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {


    @FXML
    private TextField txtTypeName;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnBack;

    @FXML
    private ChoiceBox<String> cbType;

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCategoryManager();
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
            if (cbType.getValue().equalsIgnoreCase("Meal")) {
                boolean result = MealCategoryDBHelper.insertMealCategory(txtTypeName.getText(),cbType.getValue());
                if (result) {
                    Navigator.getInstance().goToCategoryManager();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Failed");
                    alert.setContentText("The table has not been added yet");
                    alert.show();
                }
            }
            if (cbType.getValue().equalsIgnoreCase("Drink")) {
                boolean result = MealCategoryDBHelper.insertDrinkCategory(txtTypeName.getText(),cbType.getValue());
                if (result) {
                    Navigator.getInstance().goToCategoryManager();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insert Failed");
                    alert.setContentText("The table has not been added yet");
                    alert.show();
                }
            }
        }
    }
    private HashMap<String, String> error = new HashMap<String, String>();
    boolean validateInfo(){
        error.clear();
        if (txtTypeName.getText().isEmpty()){
            error.put("CategoryName", "Please enter name of category!");
        }

        if (cbType.getValue() == null){
            error.put("Type", "Please choose type");
        }
        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbType.getItems().add("Meal");
        cbType.getItems().add("Drink");
    }
}
