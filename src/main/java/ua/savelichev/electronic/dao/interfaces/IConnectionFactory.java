package ua.savelichev.electronic.dao.interfaces;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionFactory {

    Connection getConnection() throws NamingException, SQLException;
}
