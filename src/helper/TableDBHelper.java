package helper;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDBHelper {

//    public static Table getTableByTableNumber(String TableNumber) {
//        String query = "Select * from table where TableNumber = ?";
//        try(Connection cnn = DatabaseHelper.getConnection();
//            PreparedStatement stm = cnn.prepareStatement(query)){
//            stm.setString(1, TableNumber);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()){
//                Integer id = rs.getInt("ID");
//                String floor = rs.getString("Floor");
//                String table = rs.getString("TableNumber");
//                String status = rs.getString("Status");
//                return new Table(id, floor, table, status);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public static ObservableList<Table> getAllTable() {
        ObservableList<Table> tablelist = FXCollections.observableArrayList();
        try (
                Connection conn = DatabaseHelper.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `table`;");) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Status");
                tablelist.add(new Table(id, floor, table, status));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tablelist;
    }
    public static ObservableList<Table> getAllTableFull() {
        ObservableList<Table> tablelist = FXCollections.observableArrayList();
        try (
                Connection conn = DatabaseHelper.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `table` where `status` = 'Full'");) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Status");
                tablelist.add(new Table(id, floor, table, status));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tablelist;
    }
    public static List<Table> getTableByStatus1(String tableStatus) {
        List<Table> listTable = new ArrayList<>();
        String query = "select * from `table` where `Status` = ? AND `Floor` = 1 ";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query)){;
             preStm.setString(1, tableStatus.toLowerCase());
             ResultSet rs = preStm.executeQuery() ;

            while (rs.next()){
                Integer ID = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Status");
                listTable.add(new Table(ID, floor, table, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listTable;
    }
    public static List<Table> getTableByStatus2(String tableStatus) {
        List<Table> listTable = new ArrayList<>();
        String query = "select * from `table` where `Status` = ? AND `Floor` = 2 ";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query)){;
            preStm.setString(1, tableStatus.toLowerCase());
            ResultSet rs = preStm.executeQuery() ;

            while (rs.next()){
                Integer ID = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Status");
                listTable.add(new Table(ID, floor, table, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listTable;
    }



        public static boolean insertTable(String floor, String tableNumber) throws SQLException{
        String query = "Insert into `table` (`Floor`, `TableNumber`,`Status`) values (?,?,?)";
        try(Connection cnn = DatabaseHelper.getConnection();
        PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
            stm.setString(1, floor);
            stm.setString(2, tableNumber);
            stm.setString(3, Table.STATUS_EMPTY);
            if (stm.executeUpdate()>0){
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()){
                    Integer id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean updateTable(String floor, String tableNumber, Integer id) throws SQLException{
        String query = "UPDATE `table` SET `Floor` = ?, `TableNumber` = ? WHERE `ID` = ? ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);){
        stm.setString(1, floor);
        stm.setString(2, tableNumber);
        stm.setInt(3, id);
        if(stm.executeUpdate() > 0){
                return true;
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static boolean deleleTable(Integer id) throws SQLException{
        String query = "DELETE FROM `table` WHERE `ID` = ?";

        try (Connection cnn = DatabaseHelper.getConnection();
                PreparedStatement stm = cnn. prepareStatement(query);){
            stm.setInt(1, id);
            if(stm.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    // --------------------------------get table 1st floor-----------------------------//
    public static List<Table> getTable1stFloor() {
        List<Table> listTable = new ArrayList<>();
        String query = "select * from `table` where Floor = 1";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query);
             ResultSet rs = preStm.executeQuery()) {

            while (rs.next()){
                Integer ID = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Status");
                listTable.add(new Table(ID, floor, table, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listTable;
    }

    // -------------------get table 2nd floor---------------------------------//
    public static List<Table> getTable2ndFloor() {
        List<Table> listTable = new ArrayList<>();
        String query = "select * from `table` where Floor = 2";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query);
             ResultSet rs = preStm.executeQuery()) {

            while (rs.next()){
                Integer ID = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Status");
                listTable.add(new Table(ID, floor, table, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listTable;
    }
    // ------------------------------get table by id----------------------------//
    public static Table getTableByID(int ID) {
        String query = "Select * from `table` where ID = ?";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query)) {
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Image");
                return new Table(id, floor, table, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static Table getTableByName(String TableNumber) {
        String query = "Select * from `table` where TableNumber = ?";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query)) {
            stm.setString(1, TableNumber);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("ID");
                String floor = rs.getString("Floor");
                String table = rs.getString("TableNumber");
                String status = rs.getString("Image");
                return new Table(id, floor, table, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static boolean updateTableStatus(int tableID, String status) {
        String sql = "UPDATE `table` SET status = ? WHERE ID = ? ";
        try (
                Connection conn = DatabaseHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, status);
            stmt.setInt(2, tableID);
            int rowUpdated = stmt.executeUpdate();
            if (rowUpdated == 1) {
                System.out.println("Update Successfully");
                return true;
            } else {
                System.out.println("Update Failse");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
  

   
}


