package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;
import ua.savelichev.electronic.domain.entity.StoragePosition;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class StorageDAO implements IStorageDAO {

    IConnectionFactory connectionFactory;
    private static final Logger log = Logger.getLogger(StorageDAO.class);
    ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");

    public StorageDAO(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Inserts new row into the table "storage"
     *
     * @param storagePosition target for creation
     */
    @Override
    public void createStoragePosition(StoragePosition storagePosition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("CREATE_STORAGE_POSITION"));

            preparedStatement.setInt(1, storagePosition.getArticle());
            preparedStatement.setInt(2, storagePosition.getAmount());

            preparedStatement.executeUpdate();
            log.debug("Storage position created: " + storagePosition);
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
     *
     * @param article target row article value
     * @return int id value
     */
    @Override
    public StoragePosition getStoragePositionByArticle(int article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StoragePosition storagePosition = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_POSITION_AMOUNT"));

            preparedStatement.setInt(1, article);

            resultSet = preparedStatement.executeQuery();

            storagePosition = new StoragePosition();

            if (resultSet.next()) {
                storagePosition.setAmount(resultSet.getInt("amount"));
            }
            storagePosition.setArticle(article);
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
        log.debug("Got storage position: " + storagePosition);
        return storagePosition;
    }

    /**
     * Updates row in the table "storage"
     *
     * @param storagePosition position for update
     */
    @Override
    public void updateStoragePosition(StoragePosition storagePosition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_POSITION_AMOUNT"));

            preparedStatement.setInt(1, storagePosition.getAmount());
            preparedStatement.setInt(2, storagePosition.getArticle());

            preparedStatement.executeUpdate();
            log.debug("Storage position updated: " + storagePosition);
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

    @Override
    public List<StoragePosition> getAllStoragePositions() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StoragePosition> storagePositions = null;
        StoragePosition storagePosition = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_ALL_FROM_STORAGE"));

            resultSet = preparedStatement.executeQuery();

            storagePositions = new ArrayList<>();


            while (resultSet.next()) {
                storagePosition = new StoragePosition();
                storagePosition.setId(resultSet.getInt("id"));
                storagePosition.setArticle(resultSet.getInt("article"));
                storagePosition.setAmount(resultSet.getInt("amount"));

                storagePositions.add(storagePosition);
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
        log.debug("Got all storage positions.");
        return storagePositions;
    }


}


