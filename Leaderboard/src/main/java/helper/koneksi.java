package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    public static Connection getConnection(){
        Connection con = null;
        try {
            System.out.println("Connecting...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/leaderboard",
                    "root",
                    "");
            System.out.println("Connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver error");
        } catch (SQLException ex){
            System.err.println("SQL error");
        }
        return con;
    }

}
