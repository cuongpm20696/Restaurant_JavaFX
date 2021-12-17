package helper;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DrinkCategory;
import model.MealCategory;

import java.sql.*;

public class MealCategoryDBHelper {

    public static ObservableList<MealCategory> getAllMealCategory() {
        ObservableList<MealCategory> mealCategoryList = FXCollections.observableArrayList();
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM `meal_category` where  `Type` = 'Meal'");) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String mealCategory = rs.getString("Name");
                String type = rs.getString("Type");
                mealCategoryList.add(new MealCategory(id, mealCategory,type));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mealCategoryList;
    }

    public static boolean insertMealCategory(String name, String type) throws SQLException {
        String query = "Insert into `meal_category` (Name, Type) values (?,?)";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stm.setString(1, name);
            stm.setString(2, type);
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

    public static boolean updateMealCategory(String name, Integer id) throws SQLException {
        String query = "UPDATE `meal_category` SET Name = ? WHERE ID = ? and `Type` = 'Meal'";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setString(1, name);
            stm.setInt(2, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteMealCategory(Integer id) throws SQLException {
        String query = "DELETE FROM `meal_category` WHERE `ID` = ? and `Type` = 'Meal'";

        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setInt(1, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
        return false;
    }

    public static MealCategory getMealCategoryByName(String name) {
        String query = "Select * from meal_category where Name = ?";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query)) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("ID");
                String mealCategory = rs.getString("Name");
                String type = rs.getString("Type");
                return new MealCategory(id, mealCategory, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<DrinkCategory> getAllDrinkCategory() {
        ObservableList<DrinkCategory> drinkCategoryList = FXCollections.observableArrayList();
        try (   Connection conn = DatabaseHelper.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `meal_category` where `type` = 'Drink';");) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String drinkCategory = rs.getString("Name");
                String type = rs.getString("Type");
                drinkCategoryList.add(new DrinkCategory(id, drinkCategory, type));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return drinkCategoryList;
    }

    public static boolean insertDrinkCategory(String name, String type) throws SQLException{
        String query = "Insert into `meal_category` (Name, Type) values (?,?)";
        try(Connection cnn = DatabaseHelper.getConnection();
            PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
            stm.setString(1, name);
            stm.setString(2, type);
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

    public static boolean updateDrinkCategory(String name, Integer id) throws SQLException{
        String query = "UPDATE `meal_category` SET `Name` = ? WHERE `ID` = ? and `Type` = 'Drink' ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);){
            stm.setString(1, name);
            stm.setInt(2, id);
            if(stm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static boolean deleteDrinkCategory(Integer id) throws SQLException{
        String query = "DELETE FROM `meal_category` WHERE `ID` = ? and `Type`='Drink'";

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

}


