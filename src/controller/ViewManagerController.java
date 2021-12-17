/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.jfoenix.controls.JFXButton;
import helper.OrderDBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Customer;
import model.MealOrder;
import model.Order;
import model.Table;
import helper.TableDBHelper;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ViewManagerController implements Initializable {

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
    private TableView<MealOrder> tbDetail;

    @FXML
    private TableColumn<MealOrder, String> tcDishes;

    @FXML
    private TableColumn<MealOrder, String> tcDishesStatus;

    @FXML
    private GridPane gpCustomer;

    @FXML
    private Label lbCustomer;

    @FXML
    private Label lbPhone;

    @FXML
    private TableView<Table> tbViewTable;

    @FXML
    private TableColumn<Table, String> tcFloor;

    @FXML
    private TableColumn<Table, String> tcTableNo;

    @FXML
    private TableColumn<Table, String> tcTableStatus;
    private ObservableList<MealOrder> listMealByTable = FXCollections.observableArrayList();
    private Customer customerOrder = null;

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
    void clickChoose(MouseEvent event) {
        Table table = tbViewTable.getSelectionModel().getSelectedItem();
        if (table != null) {
            this.listMealByTable = OrderDBHelper.getAllOrderByTableId(table.getId());
            this.customerOrder = OrderDBHelper.CustomerOrder;
            tbDetail.setItems(listMealByTable);
            tcDishes.setCellValueFactory(c -> c.getValue().nameProperty());
            tcDishesStatus.setCellValueFactory(c -> c.getValue().statusProperty());
            if (this.customerOrder != null) {
                lbCustomer.setText(customerOrder.getCustomer());
                lbPhone.setText(customerOrder.getPhone());
            }
        }
        this.customerOrder = null;
        OrderDBHelper.CustomerOrder = null;

    }

    Order order = new Order();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Table> listData = TableDBHelper.getAllTableFull();
        tbViewTable.setItems(listData);
        tcFloor.setCellValueFactory((cellData) -> cellData.getValue().getFloorProperty());
        tcTableNo.setCellValueFactory((cellData) -> cellData.getValue().getTableNumberProperty());
        tcTableStatus.setCellValueFactory((cellData) -> cellData.getValue().getStatusProperty());
    }

}
