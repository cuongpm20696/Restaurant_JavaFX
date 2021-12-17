package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    ObjectProperty<Integer> id;
    StringProperty customer;
    StringProperty phone;

    public Customer() {
        this.id = new SimpleObjectProperty<>(null);
        this.customer = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
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

    public String getCustomer() {
        return customer.get();
    }

    public StringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public Customer(Integer id, String customer, String phone) {
        this.id = new SimpleObjectProperty<>(id);
        this.customer = new SimpleStringProperty(customer);
        this.phone = new SimpleStringProperty(phone);


    }
}
