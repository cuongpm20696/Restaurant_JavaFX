package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Meal {
    public static final String MEAL_TYPE = "Meal";
    public static final String DRINK_TYPE = "Drink";
    ObjectProperty<Integer> id;
    StringProperty name;
    StringProperty type;
    StringProperty category;
    ObjectProperty<Float> price;
    StringProperty image;

    public Meal(Integer id, String name, String type,String category, Float price, String image) {
        this.id = new SimpleObjectProperty<>(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.category = new SimpleStringProperty(category);
        this.price = new SimpleObjectProperty<>(price);
        this.image = new SimpleStringProperty(image);

    }
    public Meal(){}

    public Integer getId() {
        return id.get();
    }



    public ObjectProperty<Integer> getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public String getCategory() {
        return category.get();
    }

    public StringProperty getCategoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public Float getPrice() {
        return price.get();
    }

    public ObjectProperty<Float> getPriceProperty() {
        return price;
    }

    public void setPrice(Float price) {
        this.price.set(price);
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty getImageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
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
