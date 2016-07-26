package ua.savelichev.electronic.dao;

import ua.savelichev.electronic.dao.interfaces.IOrderItemDAO;
import ua.savelichev.electronic.domain.entity.OrderItem;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO implements IOrderItemDAO {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();


    @Override
    public void createOrderItems(List<OrderItem> orderItems, int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);
            for (OrderItem orderItem : orderItems) {

                preparedStatement = connection.prepareStatement(
                        "INSERT INTO electronic.order_item (order_id, product_article,price,amount,title) VALUES(?,?,?,?,?)");

                preparedStatement.setInt(1,orderId);
                preparedStatement.setInt(2, orderItem.getProductArticle());
                preparedStatement.setInt(3, orderItem.getPrice());
                preparedStatement.setInt(4, orderItem.getAmount());
                preparedStatement.setString(5, orderItem.getTitle());

                preparedStatement.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);

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
    public OrderItem getOrderItemById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OrderItem orderItem = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM notebook WHERE id=?");

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            orderItem = new OrderItem();

            if (resultSet.next()) {

                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setProductArticle(resultSet.getInt("product_article"));
                orderItem.setPrice(resultSet.getInt("price"));
                orderItem.setAmount(resultSet.getInt("amount"));
                orderItem.setTitle(resultSet.getString("title"));
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
        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<OrderItem> orderItems = null;
        OrderItem orderItem = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM notebook WHERE order_id=?");

            preparedStatement.setInt(1, orderId);

            resultSet = preparedStatement.executeQuery();

            orderItems = new ArrayList<>();


            while (resultSet.next()) {

                orderItem = new OrderItem();

                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setProductArticle(resultSet.getInt("product_article"));
                orderItem.setPrice(resultSet.getInt("price"));
                orderItem.setAmount(resultSet.getInt("amount"));
                orderItem.setTitle(resultSet.getString("title"));

                orderItems.add(orderItem);
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
        return orderItems;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(
                    "UPDATE electronic.order_item SET order_id=?, product_article=?,price=?,amount=?,title=? WHERE id=?");

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getProductArticle());
            preparedStatement.setInt(3, orderItem.getPrice());
            preparedStatement.setInt(4, orderItem.getAmount());
            preparedStatement.setString(5, orderItem.getTitle());
            preparedStatement.setInt(6, orderItem.getId());

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
    public void deleteOrderItemById(int orderItemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM electronic.order_item WHERE id=?");

            preparedStatement.setInt(1, orderItemId);

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
    public void deleteOrderItemsByOrderId(int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM electronic.order_item WHERE order_id=?");

            preparedStatement.setInt(1, orderId);

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
