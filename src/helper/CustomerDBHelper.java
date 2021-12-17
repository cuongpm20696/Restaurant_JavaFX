package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;
import java.util.HashMap;

public class CustomerDBHelper {
    public static ObservableList<Customer> getAllCustomer() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try (
                Connection conn = DatabaseHelper.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `customer`;");) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String customer = rs.getString("Customer");
                String phone = rs.getString("Phone");

                customerList.add(new Customer(id, customer, phone));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    public static ObservableList<Customer> getListCustomerBooking(int CusId) {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "select c.Customer , c.Phone from oderdetail a, oder b, customer c, `table` d where c.ID =  b.CustomerID and a.OderID = b.ID and b.TableID = d.ID and d.ID = ?";
        try (
                Connection conn = DatabaseHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, CusId);
            HashMap<Integer, Integer> list = new HashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.put(rs.getInt("Customer"), rs.getInt("Phone"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return customers;

    }

    public static Customer updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE `customer` SET Customer = ? , Phone = ? WHERE ID = ? ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);){
            stm.setString(1, customer.getCustomer());
            stm.setString(2, customer.getPhone());
            stm.setInt(3, customer.getId());
            int updated = stm.executeUpdate();
            if(updated == 1){
                System.out.println("update successfully");
                return customer;
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
        return null;
    }
    public static boolean deleteCustomer(int id) {
        String query = "DELETE FROM `customer` WHERE `ID` = ?";

        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn. prepareStatement(query);){
            stm.setInt(1, id);
            if(stm.executeUpdate()>0){
                return true;
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }
    public static Customer getCustomerByID(int ID) {
        String query = "Select * from customer where ID = ?";
        try(Connection cnn = DatabaseHelper.getConnection();
            PreparedStatement stm = cnn.prepareStatement(query)){
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                Integer id = rs.getInt("ID");
                String name = rs.getString("Customer");
                String phone = rs.getString("Phone");

                return new Customer(id, name, phone);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
