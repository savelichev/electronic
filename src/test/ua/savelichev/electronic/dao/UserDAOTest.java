package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IUserDAO;
import ua.savelichev.electronic.domain.entity.User;

import static org.junit.Assert.assertEquals;


public class UserDAOTest {

    TableCleaner tableCleaner;
    IConnectionFactory connectionFactory;
    IUserDAO userDAO;
    User user;

    @Before
    public void init() {
        connectionFactory = ConnectionFactoryForTest.getInstance();
        userDAO = new UserDAO(connectionFactory);
        tableCleaner = new TableCleaner();
        tableCleaner.cleanTables("user");

        user = new User("test@gmail.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setCellNumber("+380631234567");
        user.setLogin("Login");
        user.setPassword("Password");
        user.setAddress("Address");
        user.setRole("test");
        user.setBlocked(false);
    }


    @Test
    public void testGetUserByEmail() throws Exception {
        userDAO.createUser(user);
        assertEquals(user, userDAO.getUserByEmail("test@gmail.com"));
    }

    @Test
    public void testCreateUser() throws Exception {
        userDAO.createUser(user);
        assertEquals(1, userDAO.getAllUsers().size());
    }

    @Test
    public void testUpdateUser() throws Exception {
        userDAO.createUser(user);
        user.setRole("TEST");
        int userId = userDAO.getUserByEmail("test@gmail.com").getId();
        user.setId(userId);
        userDAO.updateUser(user);

        assertEquals("TEST", userDAO.getUserByEmail("test@gmail.com").getRole());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        userDAO.createUser(user);
        assertEquals(1, userDAO.getAllUsers().size());
    }
}