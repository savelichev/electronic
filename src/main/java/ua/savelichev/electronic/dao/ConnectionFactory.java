package ua.savelichev.electronic.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() throws NamingException, SQLException {

        InitialContext initialContext = new InitialContext();

        Context environmentContext = (Context) initialContext.lookup("java:comp/env");

        String dataResourceName = "jdbc/electronic";

        DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);

        return dataSource.getConnection();
    }


}
