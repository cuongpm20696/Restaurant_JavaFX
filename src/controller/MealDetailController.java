package controller;

import helper.MealDBHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import main.Restaurant;
import model.ListMeal;
import model.Meal;

import java.net.URL;
import java.util.ResourceBundle;

public class MealDetailController implements Initializable {


    @FXML
    private HBox vbMealDetail;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPrice;

    @FXML
    private Label idMeal;

    @FXML
    private ImageView imgFood;


    @FXML
    void clickMeal(MouseEvent event) {
        String id = idMeal.getText().trim();
        Meal mealCurrent  = this.mealDBHelper.getMealByID(Integer.parseInt(id));
        ListMeal listMeal = new ListMeal(mealCurrent, 1);

        if(!checkExist(listMeal)){
            TableDetailController.listChoose.add(listMeal);
        } else {
            for (ListMeal listMeal1: TableDetailController.listChoose) {
                if(listMeal1.getMeal().getId() == listMeal.getMeal().getId()){
                    int total = listMeal1.getNumber() + 1;
                    listMeal1.setNumber(total);
                }
            }
        }

        TableDetailController.totalPrice.set(calcTotal());  ;

    }
    private boolean checkExist(ListMeal listMeal){
        for (ListMeal listMeal1: TableDetailController.listChoose) {
            if(listMeal1.getMeal().getId() == listMeal.getMeal().getId()){
                return true;
            }
        }
        return false;
    }
        private float calcTotal(){
        float total = 0;
        for (ListMeal listMeal: TableDetailController.listChoose) {
            total += listMeal.getMeal().getPrice() * listMeal.getNumber();
        }
        return total;
    }


    private MealDBHelper mealDBHelper = new MealDBHelper();

    private ObservableList<Meal> mealList = MealDBHelper.getAllMenu();


    public void setData(Meal meal){
//        this.mealList = Collections.singletonList(meal);
        lbName.setText(meal.getName());
        lbPrice.setText(Restaurant.CURRENCY + meal.getPrice());
        int mealId = meal.getId();
        idMeal.setText(mealId + " ");
        Image image = new Image(getClass().getResourceAsStream("/img/images/"+ meal.getImage()));
        imgFood.setImage(image) ;


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
