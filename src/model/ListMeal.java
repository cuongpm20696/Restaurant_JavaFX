package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class ListMeal {

    private Meal meal;
    private Order order;
    private ObjectProperty<Integer> number;

    public ListMeal(Meal meal, int number) {
        this.meal = meal;
        this.order = order;
        this.number = new SimpleObjectProperty<Integer>(number);
    }


    @Override
    public String toString() {
        return "ListMeal{" +
                "meal=" + meal.toString() +
                ", number=" + number +
                '}';
    }

    public Order getOrder(){
        return order;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getNumber() {
        return number.get();
    }

    public ObjectProperty<Integer>getNumberProperty() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }
}
