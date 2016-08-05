package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;

import static org.junit.Assert.*;


public class DAOFactoryTest {

    IDAOFactory daoFactory;

    @Before
    public void init() {
        daoFactory = DAOFactory.getInstance();
    }


    @Test
    public void testGetUserDAO() {
        assertTrue(daoFactory.getUserDAO() instanceof UserDAO);
    }

    @Test
    public void testGetNotebookDAO() {
        assertTrue(daoFactory.getNotebookDAO() instanceof NotebookDAO);
    }

    @Test
    public void testGetOrderDAO() {
        assertTrue(daoFactory.getOrderDAO() instanceof OrderDAO);
    }

    @Test
    public void testGetOrderItemDAO() {
        assertTrue(daoFactory.getOrderItemDAO() instanceof OrderItemDAO);
    }

    @Test
    public void testGetPhoneDAO() {
        assertTrue(daoFactory.getPhoneDAO() instanceof PhoneDAO);
    }

    @Test
    public void testGetStorageDAO() {
        assertTrue(daoFactory.getStorageDAO() instanceof StorageDAO);
    }
}