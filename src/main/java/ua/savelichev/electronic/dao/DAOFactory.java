package ua.savelichev.electronic.dao;


import ua.savelichev.electronic.dao.interfaces.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection factory takes connection from server pool.
 * Singleton pattern.
 */
public class DAOFactory implements IDAOFactory {

    private static IDAOFactory instance = new DAOFactory();

    private DAOFactory() {

    }

    public static IDAOFactory getInstance() {
        return instance;
    }



    @Override
    public IUserDAO getUserDAO() {
        return new UserDAO(ConnectionFactory.getInstance());
    }

    @Override
    public INotebookDAO getNotebookDAO() {
        return new NotebookDAO(ConnectionFactory.getInstance());
    }

    @Override
    public IOrderDAO getOrderDAO() {
        return new OrderDAO(ConnectionFactory.getInstance());
    }

    @Override
    public IOrderItemDAO getOrderItemDAO() {
        return new OrderItemDAO(ConnectionFactory.getInstance());
    }

    @Override
    public IPhoneDAO getPhoneDAO() {
        return new PhoneDAO(ConnectionFactory.getInstance());
    }

    @Override
    public IStorageDAO getStorageDAO() {
        return new StorageDAO(ConnectionFactory.getInstance());
    }


}
