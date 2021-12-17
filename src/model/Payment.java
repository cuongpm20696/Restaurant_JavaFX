package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Payment {
    ObjectProperty<Integer> id;
    ObjectProperty<Integer> tableID;
    ObjectProperty<Integer> customerID;
    ObjectProperty<Integer> MealID;
    StringProperty oderStatus;
    ObjectProperty<Float> total;

    public Payment(Integer id, Integer tableID, Integer customerID, Integer mealID, String oderStatus, Float total) {
        this.id = new SimpleObjectProperty<>(id);
        this.tableID = new SimpleObjectProperty<>(tableID);
        this.customerID = new SimpleObjectProperty<>(customerID);
        this.MealID = new SimpleObjectProperty<>(mealID);
        this.oderStatus = new SimpleStringProperty(oderStatus);
        this.total = new SimpleObjectProperty<>(total);
    }

    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public Integer getTableID() {
        return tableID.get();
    }

    public ObjectProperty<Integer> tableIDProperty() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID.set(tableID);
    }

    public Integer getCustomerID() {
        return customerID.get();
    }

    public ObjectProperty<Integer> customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID.set(customerID);
    }

    public String getOderStatus() {
        return oderStatus.get();
    }

    public StringProperty oderStatusProperty() {
        return oderStatus;
    }

    public void setOderStatus(String oderStatus) {
        this.oderStatus.set(oderStatus);
    }

    public Float getTotal() {
        return total.get();
    }

    public ObjectProperty<Float> totalProperty() {
        return total;
    }

    public void setTotal(Float total) {
        this.total.set(total);
    }

    public Integer getMealID() {
        return MealID.get();
    }

    public ObjectProperty<Integer> mealIDProperty() {
        return MealID;
    }

    public void setMealID(Integer mealID) {
        this.MealID.set(mealID);
    }
}
