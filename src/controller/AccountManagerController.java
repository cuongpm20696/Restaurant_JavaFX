package controller;

import com.jfoenix.controls.JFXButton;
import helper.AccountDBHelper;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Account;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.Navigator;

public class AccountManagerController implements Initializable {
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
    private Button btnLogOut;

    @FXML
    private TableView<Account> tbAccount;


     @FXML
    private TableColumn<Account, Integer> tcID;

    @FXML
    private TableColumn<Account, String> tcUsername;

    @FXML
    private TableColumn<Account, String> tcPassword;

    @FXML
    private TableColumn<Account, String> tcType;

    @FXML
    private TableColumn<Account, String> tcStatus;




    @FXML
    private Button btnAddAccount;

    @FXML
    private Button btnEditAccount;

    @FXML
    private Button btnDeleteAccount;

   @FXML
    void clickAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
    }

    @FXML
    void clickDrink(ActionEvent event) throws IOException {
        Navigator.getInstance().goToDrinkManager();
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
    @FXML
    void clickAddAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAddAccount();
    }

//    @FXML
//    void clickDeleteAccount(ActionEvent event) throws SQLException, IOException {
//        Account acc = tbAccount.getSelectionModel().getSelectedItem();
//        if(acc != null){
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Delete File");
//            alert.setHeaderText("Are you sure want to delete this account?");
//            Optional<ButtonType> option = alert.showAndWait();
//             if (option.get() == ButtonType.OK) {
//			AccountDBHelper.deleleAccount(acc.getId());
//                        alert.setContentText("Deleted Succesful");
//                        alert.show();
//                        Navigator.getInstance().goToAccountManager();
//		} else {
//			Navigator.getInstance().goToAccountManager();
//		}
//        } else {
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//             alert.setContentText("None of account selected");
//             alert.show();
//        }
//    }


    @FXML
    void clickEditAccount(ActionEvent event) throws IOException {
        Account acc = tbAccount.getSelectionModel().getSelectedItem();
        if(acc != null){
            Navigator.getInstance().goToEditAccount(acc);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No seclect data");
            alert.setContentText("Please select an account before do this action");
            alert.show();
        }
        
    }
    @FXML
    void clickLockAccount(ActionEvent event) throws Exception {
        Account account = tbAccount.getSelectionModel().getSelectedItem();
        if(account != null){
            AccountDBHelper.lockAccount(account.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Locked Successful");
            alert.show();
            Navigator.getInstance().goToAccountManager();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("None of account selected");
            alert.show();
        }
    }
    @FXML
    void clickUnlockAccount(ActionEvent event) throws Exception {
        Account account = tbAccount.getSelectionModel().getSelectedItem();
        if(account != null){
            AccountDBHelper.unLockAccount(account.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("UnLocked Successful");
            alert.show();
            Navigator.getInstance().goToAccountManager();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("None of account selected");
            alert.show();
        }
    }

    ObservableList<Account> accountList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Account> listData = AccountDBHelper.getAllAccount();
        accountList.addAll(listData);
        tbAccount.setItems(accountList);
        tcID.setCellValueFactory((cellData) -> cellData.getValue().getIdProperty());
        tcUsername.setCellValueFactory((cellData) -> cellData.getValue().getUsernameProperty());
        tcPassword.setCellValueFactory((cellData) -> cellData.getValue().getPasswordProperty());
        tcType.setCellValueFactory((cellData) -> cellData.getValue().getTypeProperty());
        tcStatus.setCellValueFactory((cellData) -> cellData.getValue().getStatusProperty());
    }
}
