/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.jfoenix.controls.JFXButton;
import helper.TableDBHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.MyNotify;
import model.Table;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TablesManagerController implements Initializable {

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
    private TableView<Table> tbTable;
    @FXML
    private TableColumn<Table, String> tcFloor;
    @FXML
    private TableColumn<Table, String> tcTableNumber;
    @FXML
    private Button btnAddTable;
    @FXML
    private Button btnEditTable;
    @FXML
    private Button btnDeleteTable;




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
    private void clickAddTable(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAddTable();
    }

    @FXML
    private void clickEditTable(ActionEvent event) throws IOException {
        Table tb = tbTable.getSelectionModel().getSelectedItem();
        if(tb != null){
            Navigator.getInstance().goToEditTable(tb);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No select data");
            alert.setContentText("Please select an account before do this action");
            alert.show();
        }
    }
    @FXML
    void clickDeleteTable(ActionEvent event)throws SQLException, IOException  {
        Table tb = tbTable.getSelectionModel().getSelectedItem();
        if(tb != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Table");
            alert.setHeaderText("Are you sure want to delete this table?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                boolean flag = TableDBHelper.deleleTable(tb.getId());
               if(!flag){
                   MyNotify.MyNotifyAlertError("CANNOT DELETE, TABLE ARE CURRENTLY AVAILABLE !!!");
                   return;
               }
                alert1.setContentText("Deleted Succesful");
                alert1.show();
                Navigator.getInstance().goToTableManager();
            } else {
                Navigator.getInstance().goToTableManager();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("None of table selected");
            alert.show();
        }
    }
//    ObservableList<Table> tableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Table> listData =  TableDBHelper.getAllTable();
//        List<Table> listData = TableDBHelper.getAllTable();
//        tableList.addAll(listData);
        tbTable.setItems(listData);
        tcFloor.setCellValueFactory((cellData) -> cellData.getValue().getFloorProperty());
        tcTableNumber.setCellValueFactory((cellData) -> cellData.getValue().getTableNumberProperty());
    }    
    
}
