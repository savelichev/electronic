package ua.savelichev.electronic.domain.services;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.UserDAO;
import ua.savelichev.electronic.dao.interfaces.IUserDAO;
import ua.savelichev.electronic.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    /**
     * Returns user by email.
     * Returns empty user if not found.
     *
     * @param email
     * @return User
     */
    public User getUserByEmail(String email) {
        IUserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);
        if (user.getPassword() == null) {
            log.debug("No user with email: " + email);
        } else {
            log.debug("User with email: " + email + " was found");
        }
        return user;
    }

    /**
     * Tries to create user.
     * Checks is current email allready exists. If yes returns "false", if not creates new user and returns "true".
     *
     * @param user
     * @return
     */
    public boolean createUserIfNotExist(User user) {
        IUserDAO userDAO = new UserDAO();

        if (getUserByEmail(user.getEmail()).getPassword() != null) {
            log.debug("Couldn't create user with email: " + user.getEmail() + " cause this email allready exist");
            return false;
        } else {
            userDAO.createUser(user);
            log.debug("User with email: " + user.getEmail() + " was created");
            return true;
        }
    }

    public void updateUser(User user) {
        IUserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);
        log.debug("User " + user.getEmail() + " was updated");
    }

    public List<User> getAllUsers() {
        IUserDAO userDAO = new UserDAO();
        List<User> users = new ArrayList<>();
        users=userDAO.getAllUsers();
        log.debug("Got all users");
        return users;
    }

    public void blockUser(String email) {
        User user = getUserByEmail(email);
        user.setBlocked(true);
        updateUser(user);
        log.debug("User: "+email+" was blocked");
    }

    public void unblockUser(String email) {

        User user = getUserByEmail(email);
        user.setBlocked(false);
        updateUser(user);
        log.debug("User: "+email+" was unblocked");

    }

    /**
     * Adapter for request from admin-page.jsp
     * where response must be in List<User> format </User>
     * @param email
     * @return
     */
    public List<User> getAllUsersByEmail(String email) {
        if (email.equals("")) {
            return getAllUsers();
        }
        List<User> users = new ArrayList<>();
        users.add(getUserByEmail(email));
        log.debug("Got all users with email: "+email);
        return users;
    }

}
