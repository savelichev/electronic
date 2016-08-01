package ua.savelichev.electronic.domain.services;


import org.junit.Test;
import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.dao.DAOFactoryForTest;
import ua.savelichev.electronic.dao.UserDAO;
import ua.savelichev.electronic.domain.entity.User;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService = new UserService(DAOFactoryForTest.getInstance());


    @Test
    public void testExistUserGetUserByEmail() {

        String email = "12345@gmail.com";

        User user = new User(email);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.getUserByEmail(anyString())).thenReturn(user);

        DAOFactory daoFactory =mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        UserService userService = new UserService(daoFactory);

        assertEquals(user, userService.getUserByEmail(email));
    }
}
