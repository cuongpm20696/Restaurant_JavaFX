/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author DELL
 */
public class Table {

    ObjectProperty<Integer> id;
    StringProperty floor;
    StringProperty tableNumber;
    StringProperty status;
    public static final String STATUS_EMPTY = "Empty";
    public static final String STATUS_FULL = "Full";

    public Table(Integer id, String floor, String tableNumber, String status) {
        this.id = new SimpleObjectProperty<>(id);
        this.floor = new SimpleStringProperty(floor);
        this.tableNumber = new SimpleStringProperty(tableNumber);
        this.status = new SimpleStringProperty(status);
    }
    public Table(){}

    public Integer getId() {
        return id.get();
    }
    public ObjectProperty<Integer> getIdProperty() {
        return id;
    }
    public void setId(Integer id) {
        this.id.set(id);
    }
    
    
    public String getFloor() {
        return floor.get();
    }
    public StringProperty getFloorProperty() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getTableNumber() {
        return tableNumber.get();
    }
    public StringProperty getTableNumberProperty() {
        return tableNumber;
    }
    public void setTableNumber(String tableNumber) {
        this.tableNumber.set(tableNumber);
    }

    public String getStatus() {
        return status.get();
    }
    public StringProperty getStatusProperty() {
        return status;
    }
    public String setStatus(String status) {
        this.status.set(status);
        return status;
    }
    
    
    
    
}
