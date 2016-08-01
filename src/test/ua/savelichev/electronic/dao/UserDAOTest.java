package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IUserDAO;
import ua.savelichev.electronic.domain.entity.User;

import static junit.framework.TestCase.assertEquals;

public class UserDAOTest {
    IConnectionFactory connectionFactory = ConnectionFactoryForTest.getInstance();

    IUserDAO userDAO;

    @Before
    public void setUserDAO() {
        userDAO = new UserDAO(connectionFactory);
    }

    @Test
    public void testGetUserByEmailForExistUser() {

        String email ="savelichev.n@gmail.com";

        User user = new User(email);

        assertEquals(user, userDAO.getUserByEmail(email));

    }


}
