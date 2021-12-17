package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.MealOrder;

import java.sql.*;
import java.time.LocalDate;

public class OrderDBHelper {
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

    public static boolean checkOrderIdExist(int orderId) {
        ObservableList<MealOrder> listMealOrder = FXCollections.observableArrayList();
        String sql = "select ID from oder where ID = ?  ";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return false;
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
            int rowInserted = stm.executeUpdate();
            if (rowInserted == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteOrderId(int orderID) {
        String query = "DELETE FROM `oder` WHERE `ID` = ?";

        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn. prepareStatement(query);){
            stm.setInt(1, orderID);
            if(stm.executeUpdate()>0){
                return true;
            }
        } catch (SQLException throwables){
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


    public static boolean updateOder(Integer tableID, Integer customerID, String oderStatus, String total, Integer id) throws SQLException {
        String query = "UPDATE oder set TableId = ?, CustomerID = ?, OrderStatus  = ?, Total = ? where ID = ? ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {

            stm.setInt(1, tableID);
            stm.setInt(2, customerID);
            stm.setString(3, oderStatus);
            stm.setString(4, total);
            stm.setInt(5, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public static boolean updateTotalOrder(String total, int orderId) throws SQLException {
        String query = "UPDATE oder set  Total = ? where ID = ? ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setString(1, total);
            stm.setInt(2, orderId);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
