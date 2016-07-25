package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.User;

import java.util.List;

public interface IUserDAO {


    User getUserByEmail(String email);

    void createUser(User inUser);

    void updateUser(User inUser);

    List<User> getAllUsers();
}
