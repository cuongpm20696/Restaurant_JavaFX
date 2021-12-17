package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealDBHelper {
    public static ObservableList<Meal> getAllMenu() {
        ObservableList<Meal> mealList = FXCollections.observableArrayList();
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from meal")) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String mealName = rs.getString("Name");
                String type = rs.getString("Type");
                String category = rs.getString("MealCategoryID");
                Float price = rs.getFloat("Price");
                String image = rs.getString("Image");
                mealList.add(new Meal(id, mealName, type, category, price, image));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mealList;
    }

    public static ObservableList<Meal> getAllMeal() {
        ObservableList<Meal> mealList = FXCollections.observableArrayList();
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select a.ID, a.Name,a.Type ,b.Name as Category, a.Price, a.Image " +
                     "from meal a, meal_category b " +
                     "where a.MealCategoryID = b.ID and a.Type = 'Meal';")) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String mealName = rs.getString("Name");
                String type = rs.getString("Type");
                String category = rs.getString("Category");
                Float price = rs.getFloat("Price");
                String image = rs.getString("Image");
                mealList.add(new Meal(id, mealName, type, category, price, image));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mealList;
    }

    public static Meal getMealByID(int ID) {
        String query = "Select * from meal where ID = ?";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query)) {
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("ID");
                String name = rs.getString("Name");
                String type = rs.getString("Type");
                String category = rs.getString("MealCategoryID");
                Float price = rs.getFloat("Price");
                String image = rs.getString("Image");
                return new Meal(id, name, type, category, price, image);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;

        }
        return null;
    }


    public static Meal getMealByName(String name) {
        String query = "Select * from meal where Name = ?";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query)) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("ID");
                String meal = rs.getString("Name");
                String type = rs.getString("Type");
                String category = rs.getString("MealCategoryID");
                Float price = rs.getFloat("Price");
                String image = rs.getString("Image");

                return new Meal(id, meal, type, category, price, image);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllCategory() {
        List<String> list = new ArrayList<>();
        String query = "Select name from meal_category";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
        return list;
    }

    public static List<Meal> getMealByCategory(int mealID) {
        List<Meal> listMeal = new ArrayList<>();
        String query = "select * from `meal` where `MealCategoryID` = ? ";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            ;
            preStm.setInt(1, mealID);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String meal = rs.getString("Name");
                String type = rs.getString("Type");
                String category = rs.getString("MealCategoryID");
                Float price = rs.getFloat("Price");
                String image = rs.getString("Image");
                listMeal.add(new Meal(id, meal, type, category, price, image));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listMeal;
    }

    public static boolean insertMeal(String name, Float price, String image, Integer mealCategoryID) throws SQLException {
        String query = "Insert into `meal` (Name, Price, Image, Type, MealCategoryID) values (?,?,?,?,?)";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stm.setString(1, name);
            stm.setFloat(2, price);
            stm.setString(3, image);
            stm.setString(4, Meal.MEAL_TYPE);
            stm.setInt(5, mealCategoryID);
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

    public static boolean updateMeal(String name, Float price, String image, Integer mealCategoryID, Integer id) throws SQLException {
        String query = "UPDATE `meal` SET Name = ?,  Price = ?, Image = ?,Type = ?, MealCategoryID = ? WHERE ID = ? and `type` = 'Meal' ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setString(1, name);
            stm.setFloat(2, price);
            stm.setString(3, image);
            stm.setString(4, Meal.MEAL_TYPE);
            stm.setInt(5, mealCategoryID);
            stm.setInt(6, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteMeal(Integer id) throws SQLException {
        String query = "DELETE FROM `meal` WHERE `ID` = ? and `type` = 'Meal'";

        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setInt(1, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception throwables) {
            throwables.getMessage();
            return false;
        }
        return false;
    }

    public static ObservableList<Meal> getAllDrink() {
        ObservableList<Meal> drinkList = FXCollections.observableArrayList();
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select a.ID, a.Name,a.Type, b.Name as Category, a.Price,a.Image " +
                     "from meal a, meal_category b " +
                     "where a.MealCategoryID = b.ID and a.Type = 'Drink';");) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String mealName = rs.getString("Name");
                String type = rs.getString("Type");
                String category = rs.getString("Category");
                Float price = rs.getFloat("Price");
                String image = rs.getString("Image");
                drinkList.add(new Meal(id, mealName, type, category, price, image));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return drinkList;
    }

    public static boolean insertDrink(String name, Float price, String image, Integer mealCategoryID) throws SQLException {
        String query = "Insert into `meal` (Name, Price, Image, Type, MealCategoryID) values (?,?,?,?,?)";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stm.setString(1, name);
            stm.setFloat(2, price);
            stm.setString(3, image);
            stm.setString(4, Meal.DRINK_TYPE);
            stm.setInt(5, mealCategoryID);
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

    public static boolean updateDrink(String name, Float price, String image, Integer mealCategoryID, Integer id) throws SQLException {
        String query = "UPDATE `meal` SET Name = ?,  Price = ?, Image = ?,Type = ?, MealCategoryID = ? WHERE ID = ? and `type` = 'Drink'";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setString(1, name);
            stm.setFloat(2, price);
            stm.setString(3, image);
            stm.setString(4, Meal.DRINK_TYPE);
            stm.setInt(5, mealCategoryID);
            stm.setInt(6, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDrink(Integer id) throws SQLException {
        String query = "DELETE FROM `meal` WHERE `ID` = ? and `type` = 'Drink'";

        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setInt(1, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static ObservableList<ListMeal> getListMealBooking(int tableId) {
        ObservableList<ListMeal> listMeals = FXCollections.observableArrayList();
        String sql = "select a.MealID , a.Number from oderdetail a, oder b, customer c, `table` d where c.ID =  b.CustomerID and a.OderID = b.ID and b.TableID = d.ID and d.ID = ?";
        try (
                Connection conn = DatabaseHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, tableId);
            HashMap<Integer, Integer> list = new HashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.put(rs.getInt("MealID"), rs.getInt("Number"));
            }

            for (Map.Entry<Integer, Integer> entry : list.entrySet()) {
                Meal meal = getMealByID(entry.getKey());
                ListMeal listMeal = new ListMeal(meal, entry.getValue());
                listMeals.add(listMeal);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return listMeals;

    }

    public static boolean deleteMealByOrderID(int orderID) {
        String query = "DELETE FROM `oderdetail` WHERE `OderID` = ?";

        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setInt(1, orderID);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static double totalPrice = 0;
    public static Customer customerOrder = null;
    public static String dateOrder = "";
    public static int orderIdOrder = -1;

    public static ObservableList<ListMeal> getListMealBooking1(int tableId) {
        ObservableList<ListMeal> listMeals = FXCollections.observableArrayList();
        String sql = "select a.MealID , a.Number, c.ID, c.Customer, c.Phone, b.Total, a.Date, b.ID as orderID from oderdetail a, oder b, Customer c, `table` d where c.ID =  b.CustomerID and a.OderID = b.ID and b.TableID = d.ID and d.ID = ? ";
        try (
                Connection conn = DatabaseHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, tableId);
            HashMap<Integer, Integer> list = new HashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.put(rs.getInt("MealID"), rs.getInt("Number"));
                customerOrder = new Customer();
                customerOrder.setCustomer(rs.getString("Customer"));
                customerOrder.setId(rs.getInt("ID"));
                customerOrder.setPhone(Integer.toString(rs.getInt("Phone")));
                totalPrice = Double.parseDouble(rs.getString("Total"));
                dateOrder = rs.getString("Date");
                orderIdOrder = rs.getInt("orderID");
            }

            for (Map.Entry<Integer, Integer> entry : list.entrySet()) {
                Meal meal = getMealByID(entry.getKey());
                ListMeal listMeal = new ListMeal(meal, entry.getValue());
                listMeals.add(listMeal);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return listMeals;

    }


}
