package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MealCategory {
    ObjectProperty<Integer> id;
    StringProperty mealCategory;
    StringProperty type;



    public MealCategory(Integer id, String mealCategory, String type) {
        this.id = new SimpleObjectProperty<>(id);
        this.mealCategory = new SimpleStringProperty(mealCategory);
        this.type = new SimpleStringProperty(type);
    }

    public MealCategory() {

    }


    @Override
    public String toString() {
        return this.getMealCategory();
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


    public String getMealCategory() {
        return mealCategory.get();
    }

    public StringProperty getMealCategoryProperty() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory.set(mealCategory);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty getTypeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
