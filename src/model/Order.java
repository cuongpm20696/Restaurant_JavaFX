package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Order {
    ObjectProperty<Integer> id;
    ObjectProperty<Integer> tableID;
    ObjectProperty<Integer> CustomerID;
    StringProperty oderStatus;
    StringProperty total;

    public Order(Integer id, Integer tableID, Integer customerID, String oderStatus, String total) {
        this.id = new SimpleObjectProperty<>(id) ;
        this.tableID = new SimpleObjectProperty<>(tableID) ;
        this.CustomerID = new SimpleObjectProperty<>(customerID) ;
        this.oderStatus = new SimpleStringProperty(oderStatus);
        this.total = new SimpleStringProperty(total);
    }
    public Order(){};
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
        return CustomerID.get();
    }

    public ObjectProperty<Integer> customerIDProperty() {
        return CustomerID;
    }

    public void setCustomerID(Integer customerID) {
        this.CustomerID.set(customerID);
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

    public String getTotal() {
        return total.get();
    }

    public StringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
