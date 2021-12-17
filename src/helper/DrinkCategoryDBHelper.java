package helper;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DrinkCategory;


import java.sql.*;

public class DrinkCategoryDBHelper {
//    public static Table getCategoryByTableNumber(String TableNumber) {
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

//    public static ObservableList<DrinkCategory> getAllDrinkCategory() {
//        ObservableList<DrinkCategory> drinkCategoryList = FXCollections.observableArrayList();
//        try (   Connection conn = DatabaseHelper.getConnection();
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery("SELECT * FROM `drink_category`;");) {
//            while (rs.next()) {
//                Integer id = rs.getInt("ID");
//                String drinkCategory = rs.getString("Name");
//                drinkCategoryList.add(new DrinkCategory(id, drinkCategory));
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return drinkCategoryList;
//    }


//    public static List<Table> getAllTable(){
//        List<Table> tableList = new ArrayList<>();
//        String query = "Select * from table";
//        try(Connection cnn = DatabaseHelper.getConnection();
//            PreparedStatement stm = cnn.prepareStatement(query);
//            ResultSet rs = stm.executeQuery()){
//            while (rs.next()){
//                Integer id = rs.getInt("ID");
//                String floor = rs.getString("Floor");
//                String table = rs.getString("TableNumber");
//                String status = rs.getString("Status");
//                tableList.add(new Table(id, floor, table, status));
//
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//        return tableList;
//    }
//    public static boolean insertDrinkCategory(String type) throws SQLException{
//        String query = "Insert into `drink_category` (Name) values (?)";
//        try(Connection cnn = DatabaseHelper.getConnection();
//            PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
//            stm.setString(1, type);
//            if (stm.executeUpdate()>0){
//                ResultSet rs = stm.getGeneratedKeys();
//                if (rs.next()){
//                    Integer id = rs.getInt(1);
//                }
//                return true;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }

//   public static boolean updateDrinkCategory(String type, Integer id) throws SQLException{
//        String query = "UPDATE `drink_category` SET `Name` = ? WHERE `ID` = ? ";
//        try (Connection cnn = DatabaseHelper.getConnection();
//             PreparedStatement stm = cnn.prepareStatement(query);){
//        stm.setString(1, type);
//        stm.setInt(2, id);
//        if(stm.executeUpdate() > 0){
//                return true;
//        }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }
//    public static boolean deleteDrinkCategory(Integer id) throws SQLException{
//        String query = "DELETE FROM `drink_category` WHERE `ID` = ?";
//
//        try (Connection cnn = DatabaseHelper.getConnection();
//                PreparedStatement stm = cnn. prepareStatement(query);){
//            stm.setInt(1, id);
//            if(stm.executeUpdate()>0){
//                return true;
//            }
//        } catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return false;
//    }



}


