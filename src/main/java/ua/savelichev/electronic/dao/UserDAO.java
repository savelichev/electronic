package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IUserDAO;
import ua.savelichev.electronic.domain.entity.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDAO implements IUserDAO {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static final Logger log = Logger.getLogger(OrderDAO.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");

    /**
     * Selects row from the table "user" by field "email"
     *
     * @param email target value in field "email"
     * @return User object
     */
    @Override
    public User getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("GET_USER_BY_EMAIL"));

            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setCellNumber(resultSet.getString("cell_number"));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                user.setBlocked(resultSet.getBoolean("blocked"));
            }
        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during  closing resources: " + e);
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * Inserts row into the table "user"
     *
     * @param user object with parameters for new row
     */
    @Override
    public void createUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("CREATE_USER"));

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCellNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getAddress());

            preparedStatement.executeUpdate();
        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                log.error("Exception during  closing resources: " + e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates relevant row from table "user"
     *
     * @param user new parameters for exist row
     */
    @Override
    public void updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_USER"));

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCellNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getRole());
            preparedStatement.setBoolean(9, user.getBlocked());
            preparedStatement.setInt(10, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during  closing resources: " + e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Selects all rows from table "user"
     *
     * @return List of User objects
     */
    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user;
        List<User> users = null;
        try {
            users = new ArrayList<>();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_ALL_USERS"));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();

                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setCellNumber(resultSet.getString("cell_number"));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                user.setBlocked(resultSet.getBoolean("blocked"));

                users.add(user);
            }
        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during  closing resources: " + e);
                e.printStackTrace();
            }
        }
        return users;
    }
}
