package ua.savelichev.electronic.domain.services;


import ua.savelichev.electronic.dao.UserDAO;
import ua.savelichev.electronic.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public boolean createUser(User user) {

        if (getUserByEmail(user.getEmail()).getPassword() != null) {
            return false;
        } else {
            userDAO.createUser(user);
            return true;
        }
    }

    public void updateUser(User user){

        userDAO.updateUser(user);
    }

    public List<User> getAllUsers(){

        return new UserDAO().getAllUsers();
    }

    public void blockUser(String email){

        User user = getUserByEmail(email);
        user.setBlocked(true);
        updateUser(user);
    }

    public void unblockUser(String email){

        User user = getUserByEmail(email);
        user.setBlocked(false);
        updateUser(user);

    }

    public List<User> getAllUsersByEmail(String email){
        if (email.equals("")){
            return getAllUsers();
        }
        List<User> users = new ArrayList<>();
        users.add(getUserByEmail(email));
        return users;
    }




}
