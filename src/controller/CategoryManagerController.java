package controller;

import com.jfoenix.controls.JFXButton;
import helper.MealCategoryDBHelper;
import helper.MealDBHelper;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryManagerController implements Initializable {
    @FXML
    private JFXButton btnStatistic;

    @FXML
    private JFXButton btnAccount;

    @FXML
    private JFXButton btnType;

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
    private TableView<MealCategory> tbMealType;

    @FXML
    private TableColumn<MealCategory, String> tcMealType;

    @FXML
    private Button btnAddType;

    @FXML
    private Button btnEditType;

    @FXML
    private Button btnDeleteType;

    @FXML
    private TableView<DrinkCategory> tbDrinkType;

    @FXML
    private TableColumn<DrinkCategory, String> tcDrinkType;

    @FXML
    void clickStatistic(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStatisticManager();
    }

    @FXML
    void clickAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
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
    void clickDrink(ActionEvent event) throws IOException {
        Navigator.getInstance().goToDrinkManager();
    }

    @FXML
    void clickTable(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTableManager();
    }

    @FXML
    void clickView(ActionEvent event) throws IOException {
        Navigator.getInstance().goToViewManager();
    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();
    }

    @FXML
    void clickAddType(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAddCategoryManager();
    }

    @FXML
    void clickEditType(ActionEvent event) throws IOException {
        MealCategory mealCategory = tbMealType.getSelectionModel().getSelectedItem();
        DrinkCategory drinkCategory = tbDrinkType.getSelectionModel().getSelectedItem();
        if (mealCategory != null && drinkCategory == null) {
            Navigator.getInstance().goToEditMealCategory(mealCategory);
        } else if (mealCategory == null && drinkCategory != null) {
            Navigator.getInstance().goToEditDrinkCategory(drinkCategory);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No select data");
            alert.setContentText("Please select an category before do this action");
            alert.show();
        }

    }

    @FXML
    void clickDeleteType(ActionEvent event) throws SQLException, IOException {
        MealCategory mealCategory = tbMealType.getSelectionModel().getSelectedItem();
        DrinkCategory drinkCategory = tbDrinkType.getSelectionModel().getSelectedItem();
        if (mealCategory != null && drinkCategory == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete File");
            alert.setHeaderText("Are you sure want to delete this category?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                boolean flag = MealCategoryDBHelper.deleteMealCategory(mealCategory.getId());
                if (!flag) {
                    MyNotify.MyNotifyAlertError("cannot delete this meal because something related to this");
                } else {

                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setContentText("Deleted Successful");
                    alert1.show();
                    Navigator.getInstance().goToCategoryManager();
                }
            } else {
                Navigator.getInstance().goToCategoryManager();
            }
        } else if (mealCategory == null && drinkCategory != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete File");
            alert.setHeaderText("Are you sure want to delete this category?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                MealCategoryDBHelper.deleteDrinkCategory(drinkCategory.getId());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setContentText("Deleted Succesful");
                alert1.show();
                Navigator.getInstance().goToCategoryManager();
            } else {
                Navigator.getInstance().goToCategoryManager();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("None of meal selected");
            alert.show();
        }
    }

    private boolean checkCategoryExist(String name) {
        List<String> list = MealDBHelper.getAllCategory();

        if (!list.isEmpty()) {
            for (String category : list) {
                if (category.toLowerCase().trim().equals(name.trim().toLowerCase())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<MealCategory> listMeal = MealCategoryDBHelper.getAllMealCategory();
        tbMealType.setItems(listMeal);
        tcMealType.setCellValueFactory((cellData) -> cellData.getValue().getMealCategoryProperty());
        ObservableList<DrinkCategory> listDrink = MealCategoryDBHelper.getAllDrinkCategory();
        tbDrinkType.setItems(listDrink);
        tcDrinkType.setCellValueFactory((cellData) -> cellData.getValue().getDrinkTypeProperty());
    }
}
