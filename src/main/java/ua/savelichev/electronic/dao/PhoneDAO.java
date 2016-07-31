package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IPhoneDAO;
import ua.savelichev.electronic.domain.entity.Phone;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PhoneDAO implements IPhoneDAO {

    private IConnectionFactory connectionFactory;
    private static final Logger log = Logger.getLogger(OrderDAO.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");

    public PhoneDAO(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    /**
     * Inserts row into table "phone".
     *
     * @param phone contains parameters for new row
     */
    public void createPhone(Phone phone) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("INSERT_PHONE"));

            preparedStatement.setString(1, phone.getProducer());
            preparedStatement.setString(2, phone.getModel());
            preparedStatement.setInt(3, phone.getPrice());
            preparedStatement.setString(4, phone.getDescription());
            preparedStatement.setString(5, phone.getDisplayDiagonal());
            preparedStatement.setInt(6, phone.getArticle());
            preparedStatement.setString(7, phone.getOperationSystem());
            preparedStatement.setString(8, phone.getMainCamera());
            preparedStatement.setString(9, phone.getBatteryCapacity());
            preparedStatement.setString(10, phone.getImageRef());
            preparedStatement.setString(11, phone.getCategory());

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
     * Selects row by id from table "phone".
     *
     * @param id target row value of field "id"
     * @return Phone from table "phone" with id.
     */
    public Phone getPhoneById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Phone phone = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_PHONE_BY_ID"));

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            phone = new Phone();

            if (resultSet.next()) {
                phone.setId(resultSet.getInt("id"));
                phone.setProducer(resultSet.getString("producer"));
                phone.setModel(resultSet.getString("model"));
                phone.setPrice(resultSet.getInt("price"));
                phone.setDescription(resultSet.getString("description"));
                phone.setDisplayDiagonal(resultSet.getString("display_diagonal"));
                phone.setArticle(resultSet.getInt("article"));
                phone.setOperationSystem(resultSet.getString("os"));
                phone.setMainCamera(resultSet.getString("main_camera"));
                phone.setBatteryCapacity(resultSet.getString("battery_capacity"));
                phone.setImageRef(resultSet.getString("image_ref"));
                phone.setCategory(resultSet.getString("category"));
                phone.setStorageId(resultSet.getInt("storage_id"));
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
        return phone;
    }

    /**
     * Selects row by "producer" and "model".
     * Uses in article generation.
     *
     * @param producer target row value of field "producer"
     * @param model    target row value of field "model"
     * @return int id of selected row
     */
    @Override
    public int getId(String producer, String model) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_PHONE_ID_BY_PRODUCER_AND_MODEL"));

            preparedStatement.setString(1, producer);
            preparedStatement.setString(2, model);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
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
        return id;
    }

    /**
     * Selects all rows from database.
     *
     * @return List of Phone objects.
     */
    public List<Phone> getAllPhones() {

        List<Phone> phones = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Phone phone;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_ALL_PHONES"));

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                phone = new Phone();
                phone.setId(resultSet.getInt("id"));
                phone.setProducer(resultSet.getString("producer"));
                phone.setModel(resultSet.getString("model"));
                phone.setPrice(resultSet.getInt("price"));
                phone.setDescription(resultSet.getString("description"));
                phone.setDisplayDiagonal(resultSet.getString("display_diagonal"));
                phone.setArticle(resultSet.getInt("article"));
                phone.setOperationSystem(resultSet.getString("os"));
                phone.setMainCamera(resultSet.getString("main_camera"));
                phone.setBatteryCapacity(resultSet.getString("battery_capacity"));
                phone.setImageRef(resultSet.getString("image_ref"));
                phone.setCategory(resultSet.getString("category"));
                phone.setStorageId(resultSet.getInt("storage_id"));

                phones.add(phone);
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
        return phones;
    }

    /**
     * Updates row with relevant input Phone.
     *
     * @param phone contains new parameters for relevant row.
     */
    public void updatePhone(Phone phone) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_PHONE"));

            preparedStatement.setString(1, phone.getProducer());
            preparedStatement.setString(2, phone.getModel());
            preparedStatement.setInt(3, phone.getPrice());
            preparedStatement.setString(4, phone.getDescription());
            preparedStatement.setString(5, phone.getDisplayDiagonal());
            preparedStatement.setInt(6, phone.getArticle());
            preparedStatement.setString(7, phone.getOperationSystem());
            preparedStatement.setString(8, phone.getMainCamera());
            preparedStatement.setString(9, phone.getBatteryCapacity());
            preparedStatement.setString(10, phone.getImageRef());
            preparedStatement.setString(11, phone.getCategory());
            preparedStatement.setInt(12,phone.getStorageId());
            preparedStatement.setInt(13, phone.getId());

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
     * Deletes row in database by field "article"
     *
     * @param article value of target row field "article"
     */
    public void deletePhoneByArticle(int article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("DELETE_PHONE_BY_ARTICLE"));

            preparedStatement.setInt(1, article);

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
}
