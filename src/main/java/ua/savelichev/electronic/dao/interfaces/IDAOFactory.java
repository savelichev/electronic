package ua.savelichev.electronic.dao.interfaces;


import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IDAOFactory {



    IUserDAO getUserDAO();

    INotebookDAO getNotebookDAO();

    IOrderDAO getOrderDAO();

    IOrderItemDAO getOrderItemDAO();

    IPhoneDAO getPhoneDAO();

    IStorageDAO getStorageDAO();

}
