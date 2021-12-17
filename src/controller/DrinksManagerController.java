package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import helper.MealCategoryDBHelper;
import helper.MealDBHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DrinkCategory;
import model.Meal;
import model.MealCategory;
import view.Navigator;

public class DrinksManagerController implements Initializable {

    public static HashMap<String, Integer> drinkCategoryNameId = new HashMap<>();
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
    private TableView<Meal> tbDrink;

    @FXML
    private TableColumn<Meal, Integer> tcDrinkId;

    @FXML
    private TableColumn<Meal, String> tcDrinkName;

    @FXML
    private TableColumn<Meal, String> tcDrinkType;

    @FXML
    private TableColumn<Meal, Float> tcDrinkPrice;
    @FXML
    private ImageView imgDrink;



    @FXML
    private Button btnAddDrink;

    @FXML
    private Button btnEditDrink;

    @FXML
    private Button btnDeleteDrink;

     @FXML
    void clickAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
    }

    @FXML
    void clickAddDrink(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAddDrink();
    }

    @FXML
    void clickDeleteDrink(ActionEvent event) throws IOException, SQLException {
        Meal drink = tbDrink.getSelectionModel().getSelectedItem();
        if(drink != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Drink");
            alert.setHeaderText("Are you sure want to delete this drink?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                MealDBHelper.deleteDrink(drink.getId());
                alert1.setContentText("Deleted Succesful");
                alert1.show();
                Navigator.getInstance().goToDrinkManager();
            } else {
                Navigator.getInstance().goToDrinkManager();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("None of drink selected");
            alert.show();
        }
    }

    @FXML
    void clickDrink(ActionEvent event) throws IOException {
        Navigator.getInstance().goToDrinkManager();
    }

    @FXML
    void clickEditDrink(ActionEvent event) throws IOException {
        Meal drink = tbDrink.getSelectionModel().getSelectedItem();
        if(drink != null){
            Navigator.getInstance().goToEditDrink(drink);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No seclect data");
            alert.setContentText("Please select an drink before do this action");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Meal> drinkList = MealDBHelper.getAllDrink();
        List<DrinkCategory> list = MealCategoryDBHelper.getAllDrinkCategory();
        for (DrinkCategory drinkCategory : list) {
            drinkCategoryNameId.put(drinkCategory.getDrinkCategory(), drinkCategory.getId());
        }
        tbDrink.setItems(drinkList);
        tcDrinkId.setCellValueFactory((cellData) -> cellData.getValue().getIdProperty());
        tcDrinkName.setCellValueFactory((cellData) -> cellData.getValue().getNameProperty());
        tcDrinkType.setCellValueFactory((cellData) -> cellData.getValue().getCategoryProperty());
        tcDrinkPrice.setCellValueFactory((cellData) -> cellData.getValue().getPriceProperty());
        tbDrink.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    Meal drink = tbDrink.getSelectionModel().getSelectedItem();
                if(drink != null)
                {
                   Image image = new Image(getClass().getResourceAsStream("/img/images/" + drink.getImage()));
                   imgDrink.setImage(image);
                }
            }
        });
    }

}
