/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EditTableController implements Initializable {

    @FXML
    private TextField txtTableNumber;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnBack;
    @FXML
    private ChoiceBox<String> cbFloor;
    Table table = new Table();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbFloor.getItems().add("1F");
        cbFloor.getItems().add("2F");
//        cbFloor.getItems().add("3F");
    }
    public void loadData(Table table){
        cbFloor.setValue(table.getFloor());
        txtTableNumber.setText(table.getTableNumber());
        this.table = table;
    }

    @FXML
    private void clickSubmit(ActionEvent event) throws SQLException, IOException {
        if (!validateInfo()) {
            String stringError = "";
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError += entry.getValue() + "\n";
            }
            MyNotify.MyNotifyAlertError(stringError);
        } else {
            boolean result = TableDBHelper.updateTable(cbFloor.getValue(), txtTableNumber.getText(), table.getId());
            if (result) {
                Navigator.getInstance().goToTableManager();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Failed");
                alert.setContentText("The account has not been fixed yet");
                alert.show();
            }
        }
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
        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    @FXML
    private void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTableManager();
    }
    
}
