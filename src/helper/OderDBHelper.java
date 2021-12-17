package helper;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OderDBHelper {
    public static Customer CustomerOrder = null;
    public static ObservableList<MealOrder> getAllOrderByTableId(int tableId) {
        ObservableList<MealOrder> listMealOrder = FXCollections.observableArrayList();
        String sql = "select c.MealID, d.Name  ,b.OrderStatus, e.Customer, e.Phone, e.ID from `table` a,  oder b, oderdetail c, meal d, customer e where a.ID = b.TableID and b.ID = c.OderID and c.MealID = d.ID and e.ID =  b.CustomerID and a.ID = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, tableId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CustomerOrder = new Customer(rs.getInt("ID"), rs.getString("Customer"), Integer.toString(rs.getInt("Phone")));
                MealOrder meal = new MealOrder();
                meal.setId(rs.getInt("MealID"));
                meal.setName(rs.getString("Name"));
                meal.setStatus(rs.getString("OrderStatus"));
                listMealOrder.add(meal);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listMealOrder;
    }

    public static Customer insertCustomer(Customer customer) throws SQLException {
        String query = "Insert into customer (`Customer`, `Phone`) values (?,?)";
        ResultSet key = null;
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, customer.getCustomer());
            stmt.setString(2, customer.getPhone());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                customer.setId(newKey);
                System.out.println("Insert Successfully");
                return customer;
            } else {
                System.out.println("Insert Fail");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean insertOder(int Number, int MealID, int OderID, LocalDate date) throws SQLException {
        String query = "Insert into oderdetail (`Number`, `MealID`,`OderID`, `Date`) values (?,?,?,?)";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stm.setInt(1, Number);
            stm.setInt(2, MealID);
            stm.setInt(3, OderID);
            stm.setDate(4, Date.valueOf(date));
            if (stm.executeUpdate() > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static Integer insertOderTable(int tableID, int customerID, String orderStatus, String total) throws SQLException {
        String query = "Insert into oder (`TableID`, `CustomerID`,`OrderStatus`,`Total`) values (?,?,?,?)";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, tableID);
            stmt.setInt(2, customerID);
            stmt.setString(3, orderStatus);
            stmt.setString(4, total);
            ResultSet key = null;
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                System.out.println("Insert Successfully");
                return newKey;
            } else {
                System.out.println("Insert Fail");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Order> getAllOder() {
        List<Order> listOrder = new ArrayList<>();
        String query = "select * from oder";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query);
             ResultSet rs = preStm.executeQuery()) {

            while (rs.next()) {
                Integer ID = rs.getInt("ID");
                Integer TableID = rs.getInt("TableID");
                Integer CustomerID = rs.getInt("CustomerID");
                String OrderStatus = rs.getString("OrderStatus");
                String Total = rs.getString("Total");
                listOrder.add(new Order(ID, TableID, CustomerID, OrderStatus, Total));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listOrder;
    }

}
