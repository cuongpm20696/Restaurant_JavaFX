package controller;

import helper.AccountDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import model.MyNotify;
import view.Navigator;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnLogin;
    private HashMap<String, String> error = new HashMap<String, String>();
    @FXML
    void clickLogin(ActionEvent event) throws IOException {
        if (!validateInfo()) {
            String stringError = "";
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError += entry.getValue() + "\n";
            }
            MyNotify.MyNotifyAlertError(stringError);
        } else {
            Account account = AccountDBHelper.getAccountByUserName(txtUsername.getText());
            if (account == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account not found");
                alert.setContentText("Username " + txtUsername.getText() + " not exitsed");
                alert.show();
            } else {
                if (txtPassword.getText().equalsIgnoreCase(account.getPassword())) {
                    if (account.getType().equalsIgnoreCase("Manager")) {
                        Navigator.getInstance().goToStatisticManager();
                    }
                    if (account.getType().equalsIgnoreCase("Staff")) {
                        if (account.getStatus().equalsIgnoreCase("Open")) {
                            Navigator.getInstance().goToChoiceTable1st();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Locked");
                            alert.setContentText("This account has been locked");
                            alert.show();
                        }

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password wrong");
                    alert.setContentText("Password not corrected");
                    alert.show();
                }
            }
        }
    }

    boolean validateInfo(){
        error.clear();
        if (txtUsername.getText().isEmpty()){
            error.put("Username", "Please enter your username!");
        }
        if (txtPassword.getText().isEmpty()){
            error.put("Password", "Please enter your password!");
        }
        if (!error.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
