package helper;

import model.Meal;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDBHelper {
    public static List<Payment> getPayment() {
        List<Payment> listPayment = new ArrayList<>();
        String query = "select * from oder";
        try (Connection cnt = DatabaseHelper.getConnection();
             PreparedStatement preStm = cnt.prepareStatement(query);
             ResultSet rs = preStm.executeQuery()) {

            while (rs.next()){
                Integer ID = rs.getInt("ID");
                Integer TableID = rs.getInt("TableID");
                Integer CustomerID = rs.getInt("CustomerID");
                Integer MealID = rs.getInt("MealID");
                String OderStatus = rs.getString("OderStatus");
                Float Total = rs.getFloat("Total");
                listPayment.add(new Payment(ID, TableID, CustomerID, MealID, OderStatus, Total));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listPayment;
    }
}
