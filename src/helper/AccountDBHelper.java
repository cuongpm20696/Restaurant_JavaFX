package helper;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AccountDBHelper {
    public static Account getAccountByUserName(String Account) {
        String query = "Select * from account where Account = ?";
        try(Connection cnn = DatabaseHelper.getConnection();
            PreparedStatement stm = cnn.prepareStatement(query)){
            stm.setString(1, Account);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                Integer id = rs.getInt("ID");
                String username = rs.getString("Account");
                String password = rs.getString("Password");
                String type = rs.getString("Type");
                String status = rs.getString("Status");
                return new Account(id, username, password, type, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public static List<Account> getAllAccount(){
        List<Account> accountList = new ArrayList<>();
        String query = "Select * from account";
        try(Connection cnn = DatabaseHelper.getConnection();
            PreparedStatement stm = cnn.prepareStatement(query);
            ResultSet rs = stm.executeQuery()){
            while (rs.next()){
                Integer id = rs.getInt("ID");
                String username = rs.getString("Account");
                String password = rs.getString("Password");
                String type = rs.getString("Type");
                String status = rs.getString("Status");
                
                accountList.add(new Account(id, username, password, type, status));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return accountList;
    }
    public static boolean insertAccount(String username, String password, String type) throws SQLException{

        String query = "Insert into account (`Account`, `Password`, `Type`, `Status`) values (?,?,?,?)";
        try(Connection cnn = DatabaseHelper.getConnection();
            PreparedStatement stm = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, type);
            stm.setString(4, Account.STATUS_OPEN);
            if(stm.executeUpdate()>0){
                ResultSet rs = stm.getGeneratedKeys();
                if(rs.next()){
                    Integer id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean updateAccount(String username, String password, String type, Integer id) throws SQLException{
        String query = "UPDATE account SET `Account` = ?, `Password` = ?, `Type` = ? WHERE `Id` = ? ";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);){
        stm.setString(1, username);
        stm.setString(2, password);
        stm.setString(3, type);
        stm.setInt(4, id);
        if(stm.executeUpdate() > 0){
                return true;
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
//    public static boolean deleleAccount(Integer id) throws SQLException{
//        String query = "DELETE FROM `account` WHERE `Id` = ?";
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
    public static boolean lockAccount(Integer id) throws Exception{
    String query = "UPDATE account SET status = 'Lock' where id = ?";
    try (Connection cnn = DatabaseHelper.getConnection();
         PreparedStatement stm = cnn.prepareStatement(query);) {
        stm.setInt(1, id);
        if (stm.executeUpdate()>0){
            return true;
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return false;
}
    public static boolean unLockAccount(Integer id) throws Exception{
        String query = "UPDATE account SET status = 'Open' where id = ?";
        try (Connection cnn = DatabaseHelper.getConnection();
             PreparedStatement stm = cnn.prepareStatement(query);) {
            stm.setInt(1, id);
            if (stm.executeUpdate()>0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    }
