package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/bandienthoai";
    private final String JDBC_USERNAME = "root";
<<<<<<< HEAD
    private final String JDBC_PASSWORD = "quochuy248@gmail";
=======

    private final String JDBC_PASSWORD = "11061999";

 

>>>>>>> 67bd433eda3e7e6fb7edf9248e241a8ba5f494dc


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
