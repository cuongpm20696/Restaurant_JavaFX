package controller;

import helper.TableDBHelper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Table;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChoiceTable2ndController implements Initializable {
    @FXML
    private GridPane grid;

    @FXML
    private Button btnLogout;

    @FXML
    private ChoiceBox<String> cbChooseFloor2;

    @FXML
    private ChoiceBox<String> cbFilter2;

    @FXML
    private Button btn2nd;

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();
    }

    private String[] Floor2 ={"1st FLOOR", "2nd FLOOR"};
    private String[] Status2 = {"Empty", "Full"};

    public void setFloor (ActionEvent event) {
        String label = cbChooseFloor2.getValue();
        try {
            if (label == "1st FLOOR"){
                Navigator.getInstance().goToChoiceTable1st();
            } else if (label == "2nd FLOOR") {
                Navigator.getInstance().goToChoiceTable2nd();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showTalbeByStatus() {
        cbFilter2.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    displayTable(newValue);
                } );

    }

    public void displayTable(String status) {
        ObservableList<Table> listData = FXCollections.observableArrayList();

        List<Table> listTable = null;
        if (status.toLowerCase().equals("all")) {
            listTable = TableDBHelper.getTable2ndFloor();
        } else {
            listTable = TableDBHelper.getTableByStatus2(status.toLowerCase());
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
        cbChooseFloor2.getItems().addAll(Floor2);
        cbChooseFloor2.getSelectionModel().select(1);
        cbChooseFloor2.setOnAction(this::setFloor);
        displayTable("All");

        ObservableList<String> listStatus = FXCollections.observableArrayList(new String[]{ "All","Full", "Empty", "Booked"});
        cbFilter2.setItems(listStatus);
        cbFilter2.setValue("All");
        showTalbeByStatus();

    }
}
