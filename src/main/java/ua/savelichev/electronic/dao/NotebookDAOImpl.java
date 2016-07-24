package ua.savelichev.electronic.dao;

import ua.savelichev.electronic.dao.ConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.NotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class NotebookDAOImpl implements NotebookDAO {
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    public void createNotebook(Notebook notebook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO notebook(producer, model, price, description, display_diagonal, article, processor, ram, hdd, image_ref,category) VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, notebook.getProducer());
            preparedStatement.setString(2, notebook.getModel());
            preparedStatement.setInt(3, notebook.getPrice());
            preparedStatement.setString(4, notebook.getDescription());
            preparedStatement.setString(5, notebook.getDisplayDiagonal());
            preparedStatement.setInt(6, notebook.getArticle());
            preparedStatement.setString(7, notebook.getProcessor());
            preparedStatement.setInt(8, notebook.getRam());
            preparedStatement.setInt(9, notebook.getHdd());
            preparedStatement.setString(10,notebook.getImageRef());
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

            preparedStatement = connection.prepareStatement("SELECT * FROM notebook WHERE id=?");

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            notebook = new Notebook();

            while (resultSet.next()) {

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
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int id=-1;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id FROM notebook WHERE producer=? AND model=?");
            preparedStatement.setString(1,notebook.getProducer());
            preparedStatement.setString(2,notebook.getModel());
            resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
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

            preparedStatement = connection.prepareStatement("SELECT * FROM notebook");

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

            preparedStatement = connection.prepareStatement(
                    "UPDATE notebook SET producer=?,model=?,price=?,description=?,display_diagonal=?,article=?,processor=?,ram=?,hdd=?,image_ref=? WHERE id=?");

            preparedStatement.setString(1, notebook.getProducer());
            preparedStatement.setString(2, notebook.getModel());
            preparedStatement.setInt(3, notebook.getPrice());
            preparedStatement.setString(4, notebook.getDescription());
            preparedStatement.setString(5, notebook.getDisplayDiagonal());
            preparedStatement.setInt(6, notebook.getArticle());
            preparedStatement.setString(7, notebook.getProcessor());
            preparedStatement.setInt(8, notebook.getRam());
            preparedStatement.setInt(9, notebook.getHdd());
            preparedStatement.setString(10,notebook.getImageRef());
            preparedStatement.setInt(11,notebook.getId());

            System.out.println("from updateNotobook"+notebook);

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
            preparedStatement = connection.prepareStatement("DELETE FROM notebook WHERE article=?");
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
