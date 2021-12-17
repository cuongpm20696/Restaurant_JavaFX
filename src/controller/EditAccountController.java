/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import helper.AccountDBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EditAccountController implements Initializable {

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
    Account account = new Account();
    

    @FXML
    private void clickSubmit(ActionEvent event) throws IOException, SQLException {
        if (!validateInfo()) {
            String stringError = "";
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError += entry.getValue() + "\n";
            }
            MyNotify.MyNotifyAlertError(stringError);
        } else {
            boolean result = AccountDBHelper.updateAccount(txtUserName.getText(), txtPassword.getText(), cbType.getValue(), account.getId());
            if (result) {
                Navigator.getInstance().goToAccountManager();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Failed");
                alert.setContentText("The account has not been fixed yet");
                alert.show();
            }
        }
    }

    @FXML
    private void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
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
        cbType.getItems().add("Manager");
        cbType.getItems().add("Staff");
    }    
    public void loadData(Account account){
        txtUserName.setText(account.getUsername());
        txtPassword.setText(account.getPassword());
        cbType.setValue(account.getType());
        this.account = account;
    }
    
}
