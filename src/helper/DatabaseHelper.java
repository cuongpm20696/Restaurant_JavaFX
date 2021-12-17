package helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseHelper {
    final static String userName = "root";
    final static String password = "";
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/project_restaurant";
        return DriverManager.getConnection(url,userName,password);
    }
}
