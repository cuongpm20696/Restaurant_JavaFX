/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import helper.TableDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.MyNotify;
import model.Table;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddTableController implements Initializable {

    @FXML
    private TextField txtTableNumber;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnBack;
    @FXML
    private ChoiceBox<String> cbFloor;

    @FXML
    private void clickSubmit(ActionEvent event) throws IOException, SQLException {
        if (!validateInfo()) {
            String stringError = "";
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError += entry.getValue() + "\n";
            }
            MyNotify.MyNotifyAlertError(stringError);
        } else {
            boolean result = TableDBHelper.insertTable(cbFloor.getValue(), txtTableNumber.getText());
            if (result) {
                Navigator.getInstance().goToTableManager();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insert Failed");
                alert.setContentText("The table has not been added yet");
                alert.show();
            }
        }
    }

    @FXML
    private void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTableManager();
    }
    private HashMap<String, String> error = new HashMap<String, String>();
    boolean validateInfo(){
        error.clear();
        if (cbFloor.getValue() == null){
            error.put("Floor", "Please choose floor");
        }
        if (txtTableNumber.getText().isEmpty()){
            error.put("TableNumber", "Please enter your table number!");
        }
        Table table = TableDBHelper.getTableByName(txtTableNumber.getText().trim());
        if(table != null){
            error.put("TableNumberExist", "TableNumber already exists");
        }
        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbFloor.getItems().add("1F");
        cbFloor.getItems().add("2F");
//        cbFloor.getItems().add("3F");
    }    
}
