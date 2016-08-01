package ua.savelichev.electronic.domain.services;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IUserDAO;
import ua.savelichev.electronic.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    private IDAOFactory daoFactory;

    public UserService(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Gets user from database by email.
     * Returns null if not found.
     *
     * @param email target user email
     * @return User
     */
    public User getUserByEmail(String email) {
        IUserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            log.debug("No user with email: " + email);
            return null;
        } else {
            log.debug("User with email: " + email + " was found");
        }
        return user;
    }

    /**
     * Creates inUser if not exist.
     * Checks is current email allready exists.
     * If yes returns "false", if not creates new inUser and returns "true".
     *
     * @param inUser inUser for creating
     * @return boolean result of creating
     */
    public boolean createUserIfNotExist(User inUser) {

        User userFromDB = getUserByEmail(inUser.getEmail());

        if (userFromDB != null) {
            log.debug("Couldn't create inUser with email: " + inUser.getEmail() + " cause this email allready exist");
            return false;
        } else {
            IUserDAO userDAO = daoFactory.getUserDAO();
            userDAO.createUser(inUser);

            log.debug("User with email: " + inUser.getEmail() + " was created");
            return true;
        }
    }

    /**
     * Updates info about User
     *
     * @param user User with new parameters
     */
    public void updateUser(User user) {
        IUserDAO userDAO = daoFactory.getUserDAO();
        userDAO.updateUser(user);
        log.debug("User " + user.getEmail() + " was updated");
    }

    /**
     * Gets all Users
     *
     * @return List of User
     */
    public List<User> getAllUsers() {
        IUserDAO userDAO = daoFactory.getUserDAO();
        List<User> users = new ArrayList<>();
        users = userDAO.getAllUsers();
        log.debug("Got all users");
        return users;
    }

    /**
     * Blocks User by email
     *
     * @param email email of User
     */
    public void blockUser(String email) {
        User user = getUserByEmail(email);
        user.setBlocked(true);
        updateUser(user);
        log.debug("User: " + email + " was blocked");
    }

    /**
     * Unblocks User by email
     *
     * @param email email of User
     */
    public void unblockUser(String email) {
        User user = getUserByEmail(email);
        user.setBlocked(false);
        updateUser(user);
        log.debug("User: " + email + " was unblocked");
    }

    /**
     * Adapter for request from admin-page.jsp
     * where response must be in List<User> format </User>
     *
     * @param email email of User
     * @return List of User
     */
    public List<User> getAllUsersByEmail(String email) {

        if (email.equals("")) {
            return getAllUsers();
        }

        List<User> users = new ArrayList<>();
        users.add(getUserByEmail(email));
        log.debug("Got all users with email: " + email);
        return users;
    }

}
