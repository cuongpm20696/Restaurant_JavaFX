package controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import helper.MealCategoryDBHelper;
import helper.MealDBHelper;
import helper.TableDBHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import view.Navigator;

public class MealsManagerController implements Initializable {

    public static HashMap<String, Integer> mealCategoryNameId = new HashMap<>();
    @FXML
    private JFXButton btnStatistic;

    @FXML
    private JFXButton btnAccount;

    @FXML
    private JFXButton btnCategory;

    @FXML
    private JFXButton btnMeal;

    @FXML
    private JFXButton btnDrink;

    @FXML
    private JFXButton btnTable;

    @FXML
    private JFXButton btnView;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView<Meal> tbMeal;

    @FXML
    private TableColumn<Meal, Integer> tcMealId;

    @FXML
    private TableColumn<Meal, String> tcMealName;

    @FXML
    private TableColumn<Meal, String> tcMealType;

    @FXML
    private TableColumn<Meal, Float> tcMealPrice;

    @FXML
    private ImageView imgMeal;
    @FXML
    private Button btnAddMeal;

    @FXML
    private Button btnEditMeal;

    @FXML
    private Button btnDeleteMeal;

    @FXML
    void clickAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
    }

    @FXML
    void clickAddMeal(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAddMeal();
    }

    @FXML
    void clickDeleteMeal(ActionEvent event) throws IOException, SQLException {
        Meal meal = tbMeal.getSelectionModel().getSelectedItem();
        if (meal != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Meal");
            alert.setHeaderText("Are you sure want to delete this meal?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                boolean flag = MealDBHelper.deleteMeal(meal.getId());
                if (!flag) {
                    MyNotify.MyNotifyAlertError("cannot delete meal !!!");
                } else {
                    alert1.setContentText("Deleted Succesful");
                    alert1.show();
                    Navigator.getInstance().goToMealManager();
                }
            } else {
                Navigator.getInstance().goToMealManager();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("None of meal selected");
            alert.show();
        }
    }

    @FXML
    void clickDrink(ActionEvent event) throws IOException {
        Navigator.getInstance().goToDrinkManager();
    }

    @FXML
    void clickEditMeal(ActionEvent event) throws IOException {
        Meal meal = tbMeal.getSelectionModel().getSelectedItem();
        if (meal != null) {
            Navigator.getInstance().goToEditMeal(meal);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No seclect data");
            alert.setContentText("Please select an meal before do this action");
            alert.show();
        }
    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();
    }

    @FXML
    void clickCategory(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCategoryManager();
    }

    @FXML
    void clickMeal(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMealManager();
    }

    @FXML
    void clickStatistic(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStatisticManager();
    }

    @FXML
    void clickTable(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTableManager();
    }


    @FXML
    void clickView(ActionEvent event) throws IOException {
        Navigator.getInstance().goToViewManager();
    }

    ObservableList<Meal> mealList = MealDBHelper.getAllMeal();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<MealCategory> list = MealCategoryDBHelper.getAllMealCategory();
        for (MealCategory mealCategory : list) {
            mealCategoryNameId.put(mealCategory.getMealCategory(), mealCategory.getId());
        }
        tbMeal.setItems(mealList);
        tcMealId.setCellValueFactory((cellData) -> cellData.getValue().getIdProperty());
        tcMealName.setCellValueFactory((cellData) -> cellData.getValue().getNameProperty());
        tcMealType.setCellValueFactory((cellData) -> cellData.getValue().getCategoryProperty());
        tcMealPrice.setCellValueFactory((cellData) -> cellData.getValue().getPriceProperty());

        tbMeal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                Meal meal = tbMeal.getSelectionModel().getSelectedItem();
                if (meal != null) {
                    Image image = new Image(getClass().getResourceAsStream("/img/images/" + meal.getImage()));
                    imgMeal.setImage(image);
                }
            }
        });
    }

}
