package view;

import controller.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import model.*;

public class Navigator {
    private Navigator(){}
    static final String LOGIN = "LoginUI.fxml";
    static final String STATISTIC_MANAGER = "StatisticManagerUI.fxml";
    
    static final String ACCOUNT_MANAGER = "AccountManagerUI.fxml";
    static final String ADD_ACCOUNT = "AddAccountUI.fxml";
    static final String EDIT_ACCOUNT = "EditAccountUI.fxml";

    static final String CATEGORY_MANAGER = "CategoryManagerUI.fxml";
    static final String ADD_CATEGORY = "AddCategoryUI.fxml";
    static final String EDIT_CATEGORY = "EditCategoryUI.fxml";
    static final String MEAL_MANAGER = "MealsManagerUI.fxml";
    static final String ADD_MEAL = "AddMealUI.fxml";
    static final String EDIT_MEAL = "EditMealUI.fxml";
    static final String ADD_DRINK = "AddDrinkUI.fxml";
    static final String EDIT_DRINK = "EditDrinkUI.fxml";
    static final String DRINK_MANAGER = "DrinksManagerUI.fxml";
    
    static final String TABLE_MANAGER = "TablesManagerUI.fxml";
    static final String ADD_TABLE = "AddTableUI.fxml";
    static final String EDIT_TABLE = "EditTableUI.fxml";
    
    static final String VIEW_MANAGER = "ViewManagerUI.fxml";
    
    static final String CHOICE_TABLE_1ST = "ChoiceTable1stUI.fxml";
    static final String CHOICE_TABLE_2ND = "ChoiceTable2ndUI.fxml";
    static final String TABLE_DETAIL = "TableDetail.fxml";


    private FXMLLoader loader;
    private static Navigator navigator;
    private Stage stage;

    public static Navigator getInstance(){
        if (navigator == null){
            navigator = new Navigator();
        }
        return navigator;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void goToScene(String title, String url) throws IOException {
        loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        if (!stage.isShowing()){
            stage.show();
        }
    }
    public void goToLogin() throws IOException{
        goToScene("Login", LOGIN);
    }
    public void goToStatisticManager() throws IOException{
        goToScene("StatisticManager", STATISTIC_MANAGER);
    }
    public void goToAccountManager() throws IOException{
        goToScene("AccountManager", ACCOUNT_MANAGER );
    }
    public void goToAddAccount() throws IOException{
        goToScene("AddAccount", ADD_ACCOUNT);
    }
    public void goToEditAccount(Account acc) throws IOException {
        goToScene("EditAccount", EDIT_ACCOUNT);
        EditAccountController controller = loader.getController();
        controller.loadData(acc);
    }
    public void goToCategoryManager() throws IOException{
        goToScene("CategoryManager", CATEGORY_MANAGER);
    }
    public void goToAddCategoryManager() throws IOException{
        goToScene("AddCategory", ADD_CATEGORY);
    }
    public void goToEditMealCategory(MealCategory mealCategory) throws IOException{
        goToScene("EditCategory", EDIT_CATEGORY);
        EditCategoryController controller = loader.getController();
        controller.loadMealData(mealCategory);
    }
    public void goToEditDrinkCategory(DrinkCategory drinkCategory) throws IOException{
        goToScene("EditCategory", EDIT_CATEGORY);
        EditCategoryController controller = loader.getController();
        controller.loadDrinkData(drinkCategory);
    }
    public void goToMealManager() throws IOException{
        goToScene("MealManager", MEAL_MANAGER);
    }
    public void goToAddMeal() throws IOException{
        goToScene("AddMeal", ADD_MEAL);
    }
    public void goToEditMeal(Meal meal) throws IOException{
        goToScene("EditMeal", EDIT_MEAL);
        EditMealController controller = loader.getController();
        controller.loadData(meal);
    }
    public void goToDrinkManager() throws IOException{
        goToScene("DrinkManager", DRINK_MANAGER);
    }
    public void goToAddDrink() throws IOException{
        goToScene("AddDrink", ADD_DRINK);
    }
    public void goToEditDrink(Meal drink) throws IOException{
        goToScene("EditDrink", EDIT_DRINK);
        EditDrinkController controller = loader.getController();
        controller.loadData(drink);
    }
    public void goToTableManager() throws IOException{
        goToScene("TableManager", TABLE_MANAGER);
    }
    public void goToAddTable() throws IOException{
        goToScene("AddTable", ADD_TABLE);
    }
    public void goToEditTable(Table tb) throws IOException {
        goToScene("EditTable", EDIT_TABLE);
        EditTableController controller = loader.getController();
        controller.loadData(tb);
    }
    public void goToViewManager() throws IOException{
        goToScene("ViewManager", VIEW_MANAGER);
    }
    public void goToChoiceTable1st() throws IOException {
        goToScene("ChoiceTable1st", CHOICE_TABLE_1ST);
    }
    public void goToChoiceTable2nd() throws IOException {
        goToScene("ChoiceTable2nd", CHOICE_TABLE_2ND);
    }
    public void goToTableDetail(Table table) throws IOException {
        goToScene("TableDetail", TABLE_DETAIL);
        TableDetailController ctrl = loader.getController() ;
        ctrl.initialize(table);
    }

}
