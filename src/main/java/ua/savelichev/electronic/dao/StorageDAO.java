package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class StorageDAO implements IStorageDAO {

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static final Logger log = Logger.getLogger(StorageDAO.class);
    ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");


    /**
     * Inserts new row into the table "storage"
     * @param article Product article
     * @param amount  amount of Product
     */
    @Override
    public void createPosition(int article, int amount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("CREATE_STORAGE_POSITION"));

            preparedStatement.setInt(1, article);
            preparedStatement.setInt(2, amount);

            preparedStatement.executeUpdate();
            log.debug("Storage position created, article: " + article + " amount: " + amount);
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
     * Selects from table "storage" field id with relevant field "article"
     * @param article target row article value
     * @return int id value
     */
    @Override
    public int getPositionAmountByArticle(int article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int amount = 0;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_POSITION_AMOUNT"));

            preparedStatement.setInt(1, article);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                amount = resultSet.getInt("amount");
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
        log.debug("Got position amount: " + amount + " for article: " + article);
        return amount;
    }

    /**
     * Updates row in the table "storage"
     * @param article target row "article" field value
     * @param amount  new value for field "amount"
     */
    @Override
    public void updatePositionAmountByArticle(int article, int amount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_POSITION_AMOUNT"));

            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, article);

            preparedStatement.executeUpdate();
            log.debug("Set position amount: " + amount + " for article: " + article);
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
