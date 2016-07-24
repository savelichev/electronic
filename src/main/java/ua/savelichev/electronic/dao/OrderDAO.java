package ua.savelichev.electronic.dao;


import ua.savelichev.electronic.dao.interfaces.IOrderDAO;
import ua.savelichev.electronic.domain.entity.Order;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO electronic.order(user_id, comment, is_done) VALUES(?,?,?,?)");

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getComment());
            preparedStatement.setBoolean(3, order.isDone());


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

    @Override
    public Order getOrderById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order order = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM electronic.order WHERE id=?");

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            order = new Order();

            if (resultSet.next()) {

                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setComment(resultSet.getString("comment"));
                order.setDone(resultSet.getBoolean("is_done"));

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
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        Order order = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM electronic.order");

            resultSet = preparedStatement.executeQuery();

            orders = new ArrayList<>();

            while (resultSet.next()) {

                order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setComment(resultSet.getString("comment"));
                order.setDone(resultSet.getBoolean("is_done"));

                orders.add(order);
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
        return orders;
    }


    @Override
    public void updateOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(
                    "UPDATE electronic.order SET user_id=?,comment=?,is_done=? WHERE id=?");

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getComment());
            preparedStatement.setBoolean(3, order.isDone());


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

    @Override
    public void deleteOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM electronic.order WHERE id=?");
            preparedStatement.setInt(1, order.getId());

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
}
