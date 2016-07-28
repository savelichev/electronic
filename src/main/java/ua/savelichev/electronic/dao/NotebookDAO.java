package ua.savelichev.electronic.dao;

import ua.savelichev.electronic.dao.interfaces.INotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class NotebookDAO implements INotebookDAO {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");

    public void createNotebook(Notebook notebook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("INSERT_NOTEBOOK"));

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

            preparedStatement.executeUpdate();

        } catch (SQLException | NamingException e) {
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
                e.printStackTrace();
            }
        }

    }

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
            }


        } catch (SQLException | NamingException e) {
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
                e.printStackTrace();
            }
        }
        return notebook;
    }

    @Override
    public int getId(Notebook notebook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_ID_BY_PRODUCER_AND_MODEL"));
            preparedStatement.setString(1, notebook.getProducer());
            preparedStatement.setString(2, notebook.getModel());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }


        } catch (SQLException | NamingException e) {
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
                e.printStackTrace();
            }
        }
        return id;
    }


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

                notebooks.add(notebook);
            }
        } catch (SQLException | NamingException e) {
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
                e.printStackTrace();
            }
        }

        return notebooks;
    }

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
            preparedStatement.setInt(11, notebook.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException | NamingException e) {
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
                e.printStackTrace();
            }
        }
    }

    public void deleteNotebookByArticle(int article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(bundle.getString("DELETE_NOTEBOOK_BY_ARTICLE"));
            preparedStatement.setInt(1, article);

            preparedStatement.executeUpdate();


        } catch (SQLException | NamingException e) {
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
                e.printStackTrace();
            }
        }
    }


    public List<Notebook> getNotebooksByPrice(int minPrice, int maxPrice) {
        return null;
    }

    public List<Notebook> getNotebooksByDiagonal(int minDiagonal, int maxDiagonal) {
        return null;
    }
}
