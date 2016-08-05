package ua.savelichev.electronic.domain.services;


import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IUserDAO;
import ua.savelichev.electronic.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {


    @Test
    public void testExistUserGetUserByEmail() {

        String email = "test@gmail.com";

        User user = new User(email);

        IUserDAO userDAO = mock(IUserDAO.class);
        when(userDAO.getUserByEmail(anyString())).thenReturn(user);

        IDAOFactory daoFactory = mock(IDAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        UserService userService = new UserService(daoFactory);

        assertEquals(user, userService.getUserByEmail(email));
    }


    @Test
    public void testNoExistUserGetUserByEmail() {

        String email = "test@gmail.com";

        User user = new User("anyString" + email);

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenReturn(null);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        assertNotEquals(user, userService.getUserByEmail(email));
    }

    @Test
    public void testExistUserCreateUserIfNotExist() {

        String email = "test@gmail.com";

        User user = new User(email);

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenReturn(user);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        assertFalse(userService.createUserIfNotExist(user));
    }

    @Test
    public void testNotExistUserCreateUserIfNotExist() {

        String email = "test@gmail.com";

        User user = new User(email);

        UserService mockUserService = mock(UserService.class);
        when(mockUserService.getUserByEmail(anyString())).thenReturn(user);

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenReturn(null);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        assertTrue(userService.createUserIfNotExist(user));
    }

    @Test
    public void testNullUserUpdateUser() {

        User user = null;

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);

        UserService userService = new UserService(mockDaoFactory);

        userService.updateUser(user);
    }

    @Test
    public void getAllUsers() {

        String email = "test@gmail.com";

        User user = new User(email);

        List<User> users = new ArrayList<>();
        users.add(user);

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getAllUsers()).thenReturn(users);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    public void testBlockNullEmail() {

        String email = null;

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenThrow(new NullPointerException());

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        userService.blockUser(email);
    }

    @Test
    public void testUnblockNullEmail() {

        String email = null;

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenThrow(new NullPointerException());

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        userService.unblockUser(email);
    }


    @Test
    public void testBlockNullUser() {

        String email = "test@gmail.com";

        User user = null;

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenReturn(user);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        userService.blockUser(email);
    }

    @Test
    public void testUnBlockNullUser() {

        String email = "test@gmail.com";

        User user = null;

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(anyString())).thenReturn(user);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        userService.unblockUser(email);
    }


    @Test
    public void testGetUserByEmailAsList() {

        String email = "test@gmail.com";

        User user = new User(email);

        List<User> users = new ArrayList<>();
        users.add(user);

        IUserDAO mockUserDAO = mock(IUserDAO.class);
        when(mockUserDAO.getUserByEmail(email)).thenReturn(user);

        IDAOFactory mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getUserDAO()).thenReturn(mockUserDAO);

        UserService userService = new UserService(mockDaoFactory);

        assertEquals(1, userService.getUserByEmailAsList(email).size());
    }

}
