package controller;

import helper.MealCategoryDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.DrinkCategory;
import model.MealCategory;
import model.MyNotify;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EditCategoryController implements Initializable {
    @FXML
    private TextField txtTypeName;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnBack;

    @FXML
    private Label lbType;


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
            boolean result;
            if (lbType.getText().equalsIgnoreCase("Meal")) {
                result = MealCategoryDBHelper.updateMealCategory(txtTypeName.getText(), meal.getId());
                if (result) {
                    Navigator.getInstance().goToCategoryManager();
                }
            }
            if (lbType.getText().equalsIgnoreCase("Drink")) {
                result = MealCategoryDBHelper.updateDrinkCategory(txtTypeName.getText(), drink.getId());
                if (result) {
                    Navigator.getInstance().goToCategoryManager();
                }
            }
        }
    }

    MealCategory meal = new MealCategory();
    DrinkCategory drink = new DrinkCategory();
    private HashMap<String, String> error = new HashMap<String, String>();
    boolean validateInfo(){
        error.clear();
        if (txtTypeName.getText().isEmpty()){
            error.put("CategoryName", "Please enter name of category!");
        }

        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void loadMealData(MealCategory meal){
        lbType.setText("Meal");
        txtTypeName.setText(meal.getMealCategory());
        this.meal = meal;
    }

    public void loadDrinkData(DrinkCategory drink){
        lbType.setText("Drink");
        txtTypeName.setText(drink.getDrinkCategory());
        this.drink = drink;
    }
}
