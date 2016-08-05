package ua.savelichev.electronic.dao;


import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryForTest implements IConnectionFactory {

    private static IConnectionFactory instance = new ConnectionFactoryForTest();

    private ConnectionFactoryForTest() {

    }

    public static IConnectionFactory getInstance() {
        return instance;
    }


    public Connection getConnection() throws NamingException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://192.168.1.159/test_electronic";
        Connection conn = DriverManager.getConnection(url, "sava", "savelichev");
        return conn;
    }
}
