package controller;

import helper.MealDBHelper;
import helper.TableDBHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.ListMeal;
import model.Table;
import main.Restaurant;
import view.Navigator;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseTableChildController implements Initializable {

    @FXML
    private Label lbTableNumber;

    @FXML
    private Label lbStatus;

    @FXML
    private Label idTable;
    private Table table;
    private MealDBHelper mealDBHelper = new MealDBHelper();

    @FXML
    void clickTable(MouseEvent event) throws IOException {
        Navigator.getInstance().goToTableDetail(table);


    }
    private TableDBHelper tableDBHelper = new TableDBHelper();

    private List<Table> tableList = TableDBHelper.getAllTable();
//    private List<Table> tableList1 = TableDBHelper.getTableEmpty();

    public void setBtnTable (Table table){
        this.table = table;
        this.tableList = Collections.singletonList(table);
        lbTableNumber.setText(Restaurant.TABLENAME + " "+ table.getTableNumber());
        lbStatus.setText(table.getStatus());

    }

//    public void setBtnTableEmpty (Table table){
//        this.table = table;
//        this.tableList1 = Collections.singletonList(table);
//        lbTableNumber.setText(Restaurant.TABLENAME + " "+ table.getTableNumber());
//        lbStatus.setText(table.getStatus());
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
