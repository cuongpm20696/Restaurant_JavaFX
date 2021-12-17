package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DrinkCategory {
    ObjectProperty<Integer> id;
    StringProperty drinkCategory;
    StringProperty type;


    public DrinkCategory(Integer id, String drinkCategory, String type) {
        this.id = new SimpleObjectProperty<>(id);
        this.drinkCategory = new SimpleStringProperty(drinkCategory);
        this.type = new SimpleStringProperty(type);
    }

    public DrinkCategory() {

    }
    @Override
    public String toString() {
        return this.getDrinkCategory();
    }

    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }


    public String getDrinkCategory() {
        return drinkCategory.get();
    }

    public StringProperty getDrinkTypeProperty() {
        return drinkCategory;
    }

    public void setDrinkCategory(String drinkCategory) {
        this.drinkCategory.set(drinkCategory);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

}
