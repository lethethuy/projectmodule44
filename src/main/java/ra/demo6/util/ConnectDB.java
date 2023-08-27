package ra.demo6.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/projectModule4";
    private static String USER = "root";
    private static String PASSWORD = "Thuy1234";
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
