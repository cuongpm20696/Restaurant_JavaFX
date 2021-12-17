/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import com.sun.org.apache.xerces.internal.impl.dv.xs.FullDVFactory;
import helper.MealDBHelper;
import helper.TableDBHelper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Meal;
import model.Table;
import view.Navigator;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ChoiceTable1stController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private ChoiceBox<String> cbChooseFloor;

    @FXML
    private ChoiceBox<String> cbFilter;

    @FXML
    private Button btn1st;

    @FXML
    private GridPane grid;

    @FXML
    void click1st(ActionEvent event) {

    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();

    }

    public String[] Floor = {"1st FLOOR", "2nd FLOOR"};
    public String[] Status = {"Empty", "Full"};

    public static ObservableList<Table> tableStatus = FXCollections.observableArrayList();


    public void setFloor(ActionEvent event) {
        String label = cbChooseFloor.getValue();
        try {
            if (label == "1st FLOOR") {
                Navigator.getInstance().goToChoiceTable1st();
            } else if (label == "2nd FLOOR") {
                Navigator.getInstance().goToChoiceTable2nd();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showTalbeByStatus() {
        cbFilter.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    displayTable(newValue);
                } );

    }

    public void displayTable(String status) {
        ObservableList<Table> listData = FXCollections.observableArrayList();

        List<Table> listTable = null;
        if (status.toLowerCase().equals("all")) {
            listTable = TableDBHelper.getTable1stFloor();
        } else {
            listTable = TableDBHelper.getTableByStatus1(status.toLowerCase());
        }
        assert listTable != null;
        grid.getChildren().clear();
        listData.addAll(listTable);
        int column = 0;
        int row = 1;
        try {
            for (Table table : listData) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/ChooseTableChild.fxml"));
                Pane pane = fxmlLoader.load();
                ChooseTableChildController tableCtrl = fxmlLoader.getController();
                tableCtrl.setBtnTable(table);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                grid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //showAllTable();

        cbChooseFloor.getItems().addAll(Floor);
        cbChooseFloor.getSelectionModel().select(0);
        cbChooseFloor.setOnAction(this::setFloor);
        displayTable("All");

        ObservableList<String> listStatus = FXCollections.observableArrayList(new String[]{"All","Full", "Empty", "Booked" });
        cbFilter.setItems(listStatus);
        cbFilter.setValue("All");
        showTalbeByStatus();


    }
}
