package ua.savelichev.electronic.dao;


import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory implements IConnectionFactory {

    private static IConnectionFactory instance = new ConnectionFactory();

    private ConnectionFactory() {
    }

    public static IConnectionFactory getInstance() {
        return instance;
    }

    /**
     * Looks up environment context for DataSource.
     *
     * @return SQL Connection
     * @throws NamingException
     * @throws SQLException
     */
    public Connection getConnection() throws NamingException, SQLException {

        InitialContext initialContext = new InitialContext();

        Context environmentContext = (Context) initialContext.lookup("java:comp/env");

        String dataResourceName = "jdbc/electronic";

        DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);

        return dataSource.getConnection();
    }
}
