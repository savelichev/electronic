package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.User;

import java.util.List;

public interface IUserDAO {


    /**
     * Selects row from the table "user" by field "email"
     *
     * @param email target value in field "email"
     * @return User object
     */
    User getUserByEmail(String email);

    /**
     * Inserts row into the table "user"
     *
     * @param inUser object with parameters for new row
     */
    void createUser(User inUser);

    /**
     * Updates relevant row from table "user"
     *
     * @param inUser new parameters for exist row
     */
    void updateUser(User inUser);

    /**
     * Selects all rows from table "user"
     *
     * @return List of User objects
     */
    List<User> getAllUsers();
}
