package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.INotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.services.product.ProductUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotebookDAO implements INotebookDAO {

    private IConnectionFactory connectionFactory;
    private static final Logger log = Logger.getLogger(OrderDAO.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");


    public NotebookDAO(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Inserts row into table "notebook".
     *
     * @param notebook contains parameters for new row
     */
    public void createNotebook(Notebook notebook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);
            log.debug("Transaction begin");

            preparedStatement = connection.prepareStatement(bundle.getString("INSERT_NOTEBOOK"));

            preparedStatement.setString(1, notebook.getProducer());
            preparedStatement.setString(2, notebook.getModel());
            preparedStatement.setInt(3, notebook.getPrice());
            preparedStatement.setString(4, notebook.getDescription());
            preparedStatement.setString(5, notebook.getDisplayDiagonal());
            preparedStatement.setString(6, notebook.getProcessor());
            preparedStatement.setInt(7, notebook.getRam());
            preparedStatement.setInt(8, notebook.getHdd());
            preparedStatement.setString(9, notebook.getImageRef());
            preparedStatement.setString(10, notebook.getCategory());

            preparedStatement.executeUpdate();
            log.debug("notebook inserted: " + notebook);
            preparedStatement.clearParameters();

            resultSet = preparedStatement.executeQuery(bundle.getString("SELECT_LAST_INSERTED_NOTEBOOK_ID"));
            int notebookId = 0;
            if (resultSet.next()) {
                notebookId = resultSet.getInt("id");
            }
            log.debug("Got notebook id: " + notebookId);
            preparedStatement.clearParameters();
            int notebookArticle = ProductUtils.generateProductArticle("notebook", notebookId);
            log.debug("Generated notebook article: " + notebookArticle);
            int amount = 0;
            preparedStatement = connection.prepareStatement(bundle.getString("CREATE_STORAGE_POSITION"));
            preparedStatement.setInt(1, notebookArticle);
            preparedStatement.setInt(2, amount);
            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
            log.debug("Storage position created: article= " + notebookArticle + ", amount= " + amount);

            resultSet = preparedStatement.executeQuery(bundle.getString("SELECT_LAST_INSERTED_STORAGE_ID"));
            int storageId = 0;
            if (resultSet.next()) {
                storageId = resultSet.getInt("id");
            }
            log.debug("Got storage position id: " + storageId);
            preparedStatement.clearParameters();
            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_NOTEBOOK_ARTICLE_AND_STORAGE_ID"));
            preparedStatement.setInt(1, notebookArticle);
            preparedStatement.setInt(2, storageId);
            preparedStatement.setInt(3, notebookId);
            preparedStatement.executeUpdate();
            log.debug("Notebook updated:notebookArticle= " + notebookArticle + ", storageId= " + storageId);

            connection.commit();
            log.debug("Transaction committed");
            connection.setAutoCommit(true);

        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            try {
                if (connection != null) {
                    connection.rollback();
                    log.debug("Transaction rollback");
                }
            } catch (SQLException ex) {
                log.error("Exception in rollback transaction: " + ex);
                ex.printStackTrace();
            }
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
     * Selects row by id from table "notebook".
     *
     * @param id target row value of field "id"
     * @return Notebook from table "notebook" with id.
     */
    public Notebook getNotebookById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Notebook notebook = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_NOTEBOOK_BY_ID"));

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            notebook = new Notebook();

            if (resultSet.next()) {
                notebook.setId(resultSet.getInt("id"));
                notebook.setProducer(resultSet.getString("producer"));
                notebook.setModel(resultSet.getString("model"));
                notebook.setPrice(resultSet.getInt("price"));
                notebook.setDescription(resultSet.getString("description"));
                notebook.setDisplayDiagonal(resultSet.getString("display_diagonal"));
                notebook.setArticle(resultSet.getInt("article"));
                notebook.setProcessor(resultSet.getString("processor"));
                notebook.setRam(resultSet.getInt("ram"));
                notebook.setHdd(resultSet.getInt("hdd"));
                notebook.setImageRef(resultSet.getString("image_ref"));
                notebook.setCategory(resultSet.getString("category"));
                notebook.setStorageId(resultSet.getInt("storage_id"));
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
        return notebook;
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

            preparedStatement = connection.prepareStatement(
                    bundle.getString("SELECT_NOTEBOOK_ID_BY_PRODUCER_AND_MODEL"));

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
     * @return List of Notebook objects.
     */
    public List<Notebook> getAllNotebooks() {

        List<Notebook> notebooks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Notebook notebook;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_ALL_NOTEBOOKS"));

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notebook = new Notebook();
                notebook.setId(resultSet.getInt("id"));
                notebook.setProducer(resultSet.getString("producer"));
                notebook.setModel(resultSet.getString("model"));
                notebook.setPrice(resultSet.getInt("price"));
                notebook.setDescription(resultSet.getString("description"));
                notebook.setDisplayDiagonal(resultSet.getString("display_diagonal"));
                notebook.setArticle(resultSet.getInt("article"));
                notebook.setProcessor(resultSet.getString("processor"));
                notebook.setRam(resultSet.getInt("ram"));
                notebook.setHdd(resultSet.getInt("hdd"));
                notebook.setImageRef(resultSet.getString("image_ref"));
                notebook.setCategory(resultSet.getString("category"));
                notebook.setStorageId(resultSet.getInt("storage_id"));

                notebooks.add(notebook);
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
        return notebooks;
    }

    /**
     * Updates row with relevant input Notebook.
     *
     * @param notebook contains new parameters for relevant row.
     */
    public void updateNotebook(Notebook notebook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_NOTEBOOK"));

            preparedStatement.setString(1, notebook.getProducer());
            preparedStatement.setString(2, notebook.getModel());
            preparedStatement.setInt(3, notebook.getPrice());
            preparedStatement.setString(4, notebook.getDescription());
            preparedStatement.setString(5, notebook.getDisplayDiagonal());
            preparedStatement.setInt(6, notebook.getArticle());
            preparedStatement.setString(7, notebook.getProcessor());
            preparedStatement.setInt(8, notebook.getRam());
            preparedStatement.setInt(9, notebook.getHdd());
            preparedStatement.setString(10, notebook.getImageRef());
            preparedStatement.setString(11, notebook.getCategory());
            preparedStatement.setInt(12, notebook.getId());

            preparedStatement.executeUpdate();
            log.debug("Notebook updated: "+notebook);

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
    public void deleteNotebookByArticle(int article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("DELETE_NOTEBOOK_BY_ARTICLE"));

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
