/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.jfoenix.controls.JFXButton;
import helper.*;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import model.*;
import view.Navigator;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TableDetailController {

    public static HashMap<String, Integer> mealCategoryNameId = new HashMap<>();
    @FXML
    private TableView<ListMeal> tbDetails;
    @FXML
    private TableColumn<ListMeal, Integer> tcNo;
    @FXML
    private TableColumn<ListMeal, String> tcDishes;
    @FXML
    private TableColumn<ListMeal, Integer> tcNumber;
    @FXML
    private TableColumn<ListMeal, Float> tcPrice;
    @FXML
    private ScrollPane scroll;
    @FXML
    private SplitPane split;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane grCusInfo;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdateTable;
    @FXML
    private Button btnPayment;
    @FXML
    private Label lbTotal;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private DatePicker dateInput;
    @FXML
    private ChoiceBox<String> cbFilterMeal;
    @FXML
    private Label idTable;
    @FXML
    private Label idFloor;
    @FXML
    private JFXButton btnBack;
    private HashMap<String, String> error = new HashMap<String, String>();
    private OrderDBHelper orderHelper = new OrderDBHelper();
    private Customer customerOrder = null;
    public static FloatProperty totalPrice = new SimpleFloatProperty();


    @FXML
    void clickDelete(ActionEvent event) {
        ListMeal listMeal = tbDetails.getSelectionModel().getSelectedItem();


        if (listMeal != null) {
            try {
                for (ListMeal listMeal1 : TableDetailController.listChoose) {
                    if (Objects.equals(listMeal1.getMeal().getId(), listMeal.getMeal().getId())) {
                        int total = listMeal1.getNumber() - 1;
                        listMeal1.setNumber(total);
                    }
                    if (listMeal1.getNumber() <= 0) {
                        listChoose.removeAll(listMeal1);
                    }
                }
            } catch (ConcurrentModificationException e) {

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("None of meal selected");
            alert.show();
        }
        TableDetailController.totalPrice.set(calcTotal());
    }

    @FXML
    private void clickSave(ActionEvent event) throws SQLException, IOException {
        saveTable();
//        if(totalPrice.equals(0)){
//            saveTable();
//        }else{
//            //OrderDBHelper.updateOder()
//            MyNotify.MyNotifyAlert("THIS TABLE HAS SAVED ");
//        }

    }


    @FXML
    private void clickPayment(ActionEvent event) throws IOException, SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure want to pay this table?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            if (MealDBHelper.customerOrder == null && MealDBHelper.orderIdOrder == -1) {
                listChoose.clear();
            } else {

                MealDBHelper.deleteMealByOrderID(MealDBHelper.orderIdOrder);
                OrderDBHelper.deleteOrderId(MealDBHelper.orderIdOrder);
                CustomerDBHelper.deleteCustomer(MealDBHelper.customerOrder.getId());
                TableDBHelper.updateTableStatus(tableUsed.getId(), "Empty");
            }

            Navigator.getInstance().goToChoiceTable1st();
            MyNotify.MyNotifyAlert("THANKS YOU !!!");

        } else {
            MyNotify.MyNotifyAlert("PAYMENT CANCELED !!");

        }
    }

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        listChoose.clear();
        Navigator.getInstance().goToChoiceTable1st();
    }

    public float calcTotal() {
        float total = 0;
        for (ListMeal listMeal : TableDetailController.listChoose) {
            total += listMeal.getMeal().getPrice() * listMeal.getNumber();
        }
        return total;
    }
//------------------- save data --------------------------//

    public int newOrderId;

    public void saveTable() throws SQLException, IOException {
        if (!validateInfo()) {

            StringBuilder stringError = new StringBuilder();
            for (Map.Entry<String, String> entry : error.entrySet()) {
                stringError.append(entry.getValue()).append("\n");
            }
            MyNotify.MyNotifyAlertError(stringError.toString());
        } else {
            if (MealDBHelper.orderIdOrder == -1 && MealDBHelper.customerOrder == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "DO YOU WANT TO BOOK A TABLE ?", ButtonType.YES, ButtonType.NO);
                if (alert.showAndWait().get() == ButtonType.YES) {
                    Customer customer = new Customer();
                    customer.setCustomer(txtCustomerName.getText());
                    customer.setPhone(txtPhoneNumber.getText());
                    this.customerOrder = orderHelper.insertCustomer(customer);
                    if (this.customerOrder == null) {
                        System.out.println("insert fail !!");
                        return;
                    }
                    boolean flag = true;

                    if (!TableDBHelper.updateTableStatus(tableUsed.getId(), "Full")) {
                        flag = false;
                    }
                    newOrderId = OrderDBHelper.insertOderTable(tableUsed.getId(), this.customerOrder.getId(), "pending", String.valueOf(totalPrice.get()));

                    for (ListMeal listMeal : listChoose) {
                        boolean result = OrderDBHelper.insertOder(listMeal.getNumber(), listMeal.getMeal().getId(), newOrderId, dateInput.getValue());
                        if (result) {
                            System.out.println("insert " + listMeal.getMeal().getName() + " success!");
                        } else {
                            flag = false;
                            System.out.println("insert " + listMeal.getMeal().getName() + " fail!");

                        }
                    }
                    if (flag) {
                        MyNotify.MyNotifyAlert("BOOKING SUCCESSFUL !!");
                    } else {
                        MyNotify.MyNotifyAlert("CANCEL BOOKING !!");
                    }
                    listChoose.clear();
                    totalPrice.set(0);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "DO YOU WANT TO EDIT MENU ?", ButtonType.YES, ButtonType.NO);
                if (alert.showAndWait().get() == ButtonType.YES) {
                    totalPrice.set(calcTotal());
                    Customer customer = new Customer();
                    customer.setCustomer(txtCustomerName.getText());
                    customer.setPhone(txtPhoneNumber.getText());
                    customer.setId(MealDBHelper.customerOrder.getId());
                    MealDBHelper.customerOrder = customer;
                    CustomerDBHelper.updateCustomer(customer);
                    MealDBHelper.deleteMealByOrderID(MealDBHelper.orderIdOrder);
                    OrderDBHelper.updateTotalOrder(Float.toString(totalPrice.get()), MealDBHelper.orderIdOrder);
                    MealDBHelper.totalPrice = totalPrice.get();

                    boolean flag = true;

                    for (ListMeal listMeal : listChoose) {
                        boolean result = OrderDBHelper.insertOder(listMeal.getNumber(), listMeal.getMeal().getId(), MealDBHelper.orderIdOrder, dateInput.getValue());
                        if (result) {
                            System.out.println("UPDATE " + listMeal.getMeal().getName() + " success!");
                        } else {
                            flag = false;
                            System.out.println("UPDATE " + listMeal.getMeal().getName() + " fail!");

                        }
                    }

                    if (flag) {
                        MyNotify.MyNotifyAlert("EDIT SUCCESSFUL !!");
                        Navigator.getInstance().goToChoiceTable1st();
                    } else {
                        MyNotify.MyNotifyAlert("CANCEL BOOKING !!");
                    }
                    listChoose.clear();
                    totalPrice.set(0);


                }
            }
            Navigator.getInstance().goToChoiceTable1st();

        }
    }


    // ------------- validate --------------------//
    boolean validateInfo() {
        error.clear();
        try {
        if (txtCustomerName.getText().isEmpty()) {
            error.put("customer", "Customer name can not be blank !!");
        }
        if (txtPhoneNumber.getText().isEmpty()) {
            error.put("phone", "phone number name can not be blank !!");
        }
        if (dateInput.getValue() == null) {
            error.put("date", "date time must be chose !!");
        }
        if (listChoose.isEmpty()) {
            error.put("list", "please choose at least one meal !!!");
        }} catch (Exception e){
            return false;
        }
        if (!error.isEmpty()) {
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------//
    private Table table;
    public static ObservableList<ListMeal> listChoose = FXCollections.observableArrayList();
    public static List<Customer> customers = FXCollections.observableArrayList();

    // ------------------- render dishes to table oder -----------//
    public void chooseMeal() {
        tbDetails.setItems(listChoose);
        tbDetails.setFixedCellSize(30);
        tcNo.setCellValueFactory((c) -> {
            return c.getValue().getMeal().getIdProperty();
        });
        tcDishes.setCellValueFactory((c) -> {
            return c.getValue().getMeal().getNameProperty();
        });
        tcPrice.setCellValueFactory(c -> {

            return c.getValue().getMeal().getPriceProperty();
        });
        tcNumber.setCellValueFactory(c -> {
            return c.getValue().getNumberProperty();
        });

    }

    public void cusBooked() {
        txtCustomerName.setText(customerBooked.getCustomer());
        txtPhoneNumber.setText(customerBooked.getPhone());
    }


    /**
     * Initializes the controller class.
     */
    private Table tableUsed = null;
    private Customer customerBooked = null;

    //-----------------------filter meal ------------------//
    public void showMealByStatus() {
        cbFilterMeal.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (newValue.equals("All")) {
                        displayMeal(1);
                    } else {
                        displayMeal(mealCategoryNameId.get(newValue));
                    }
                });

    }

    //-----------------------display dishes -------------//
    public void displayMeal(int mealCategoryID) {
        ObservableList<Meal> listData = FXCollections.observableArrayList();

        List<Meal> listMeal1 = null;
        if (cbFilterMeal.getValue().equals("All")) {
            listMeal1 = MealDBHelper.getAllMenu();
        } else {
            listMeal1 = MealDBHelper.getMealByCategory(mealCategoryID);
        }
        assert listMeal1 != null;
        grid.getChildren().clear();
        listData.addAll(listMeal1);
        int column = 0;
        int row = 1;
        try {
            for (Meal meal : listData) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/MealDetail.fxml"));
                Pane pane = fxmlLoader.load();
                MealDetailController mealCtrl = fxmlLoader.getController();
                mealCtrl.setData(meal);
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

    //--------------------- load existed information----------------//
    public void loadExistInfo(Table table) {
        this.tableUsed = table;
        listChoose.clear();
        idTable.setText(table.getTableNumber());
        idFloor.setText(table.getFloor());

        if (table.getStatus().equals("Empty")) {
            MealDBHelper.orderIdOrder = -1;
            MealDBHelper.customerOrder = null;
            MealDBHelper.totalPrice = 0;
            MealDBHelper.dateOrder = "";
        }

        if (!table.getStatus().equals("Empty")) {

            listChoose = MealDBHelper.getListMealBooking1(tableUsed.getId());
            if (MealDBHelper.customerOrder != null) {
                txtCustomerName.setText(MealDBHelper.customerOrder.getCustomer());
                txtPhoneNumber.setText(MealDBHelper.customerOrder.getPhone());
            }

            if (MealDBHelper.totalPrice != 0) {
                lbTotal.setText(Double.toString(MealDBHelper.totalPrice));
            }
            chooseMeal();


            if (!MealDBHelper.dateOrder.equals("")) {

                dateInput.setConverter(new StringConverter<LocalDate>() {
                    String pattern = "yyyy-MM-dd";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                    {
                        dateInput.setPromptText(MealDBHelper.dateOrder);
                        dateInput.setValue(Helper.formatDate(MealDBHelper.dateOrder));
                    }

                    @Override
                    public String toString(LocalDate date) {
                        if (date != null) {
                            return dateFormatter.format(date);
                        } else {
                            return "";
                        }
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        if (string != null && !string.isEmpty()) {
                            return LocalDate.parse(string, dateFormatter);
                        } else {
                            return null;
                        }
                    }
                });
            }
        }
    }


    public void initialize(Table table) {
        List<MealCategory> listMeal = MealCategoryDBHelper.getAllMealCategory();
        List<DrinkCategory> listDrink = MealCategoryDBHelper.getAllDrinkCategory();
        for (MealCategory mealCategory : listMeal) {
            mealCategoryNameId.put(mealCategory.getMealCategory(), mealCategory.getId());
        }
        for (DrinkCategory drinkCategory : listDrink) {
            mealCategoryNameId.put(drinkCategory.getDrinkCategory(), drinkCategory.getId());
        }

        ObservableList<String> listChoiceMeal = FXCollections.observableArrayList();
        List<MealCategory> listMealCategory = MealCategoryDBHelper.getAllMealCategory();
        List<DrinkCategory> listDrinkCategory = MealCategoryDBHelper.getAllDrinkCategory();
        listChoiceMeal.add("All");
        for (MealCategory mealCategory : listMealCategory) {
            listChoiceMeal.add(mealCategory.getMealCategory());
        }
        for (DrinkCategory drinkCategory : listDrinkCategory) {
            listChoiceMeal.add(drinkCategory.getDrinkCategory());
        }
        cbFilterMeal.setItems(listChoiceMeal);
        cbFilterMeal.setValue("All");
        showMealByStatus();
        loadExistInfo(table);
        displayMeal(1);
        chooseMeal();
        totalPrice.addListener((observable, oldValue, newValue) -> {
            lbTotal.setText(String.valueOf(newValue));
        });
        System.out.println(MealDBHelper.orderIdOrder);

    }
}




