package ua.savelichev.electronic.domain.services;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.savelichev.electronic.dao.UserDAO;
import ua.savelichev.electronic.domain.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

public class UserServiceTest {

    private UserService userService;




    @Before
    public void init() {
        userService = new UserService();

    }

//    @Test
//    public void testNullGetUserByEmail() {
//
//        String email = "123@gmail.com";
//        assertNull(userService.getUserByEmail(email));
//    }

    @Test
    public void testExistUserGetUserByEmail() {

        String email = "savelichev.n@gmail.com";
        User user = new User(email);
        assertEquals(user, userService.getUserByEmail(email));
    }
}
