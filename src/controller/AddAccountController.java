/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import helper.AccountDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Account;
import model.MyNotify;
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
public class AddAccountController implements Initializable {
    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnBack;


    @FXML
    private ChoiceBox<String> cbType;

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException, SQLException {
        if (!validateInfo()) {
            StringBuilder stringError = new StringBuilder();
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError.append(entry.getValue()).append("\n");
            }
            MyNotify.MyNotifyAlertError(stringError.toString());
        } else {
            boolean result = AccountDBHelper.insertAccount(txtUserName.getText(), txtPassword.getText(), cbType.getValue());
            if (result) {
                Navigator.getInstance().goToAccountManager();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insert Failed");
                alert.setContentText("The account has not been added yet");
                alert.show();
            }
        }
    }

    private HashMap<String, String> error = new HashMap<String, String>();
    boolean validateInfo(){
        error.clear();
        if (txtUserName.getText().isEmpty()){
            error.put("Username", "Please enter your username!");
        }
        if (txtPassword.getText().isEmpty()){
            error.put("Password", "Please enter your password!");
        }
        if (cbType.getValue() == null){
            error.put("Type", "Please choose type");
        }
        Account account = AccountDBHelper.getAccountByUserName(txtUserName.getText().trim());
        if(account != null){
            error.put("accountExist", "Account already exists !!!");
        }
        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbType.getItems().add("Manager");
        cbType.getItems().add("Staff");
    }
    
}
