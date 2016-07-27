package ua.savelichev.electronic.dao;


import ua.savelichev.electronic.dao.interfaces.IOrderDAO;
import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.entity.OrderItem;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDAO implements IOrderDAO {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLqueries");

    @Override
    public void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("CREATE_USER");

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getComment());
            preparedStatement.setBoolean(3, order.getIsDone());
            preparedStatement.setString(4, order.getBuyerName());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setString(6, order.getBuyerCellNumber());

            preparedStatement.executeUpdate();

            preparedStatement.clearParameters();


            ResultSet resultSet = preparedStatement.executeQuery("SELECT last_insert_id() AS id FROM electronic.order");
            int orderId = 0;
            if (resultSet.next()) {
                orderId = resultSet.getInt("id");
            }
            preparedStatement.clearParameters();

            for (OrderItem orderItem : order.getOrderItems()) {
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO electronic.order_item (order_id, product_article,price,amount,title) VALUES(?,?,?,?,?)");

                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, orderItem.getProductArticle());
                preparedStatement.setInt(3, orderItem.getPrice());
                preparedStatement.setInt(4, orderItem.getAmount());
                preparedStatement.setString(5, orderItem.getTitle());

                preparedStatement.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException | NamingException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
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
                order.setIsDone(resultSet.getBoolean("is_done"));
                order.setBuyerName(resultSet.getString("buyer_name"));
                order.setAddress(resultSet.getString("address"));
                order.setBuyerCellNumber(resultSet.getString("buyer_cell_number"));

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
    public List<Order> getOrdersByUserId(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        Order order = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM electronic.order WHERE user_id=?");

            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            orders = new ArrayList<>();

            while (resultSet.next()) {

                order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setComment(resultSet.getString("comment"));
                order.setIsDone(resultSet.getBoolean("is_done"));
                order.setBuyerName(resultSet.getString("buyer_name"));
                order.setAddress(resultSet.getString("address"));
                order.setBuyerCellNumber(resultSet.getString("buyer_cell_number"));

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
                order.setIsDone(resultSet.getBoolean("is_done"));
                order.setBuyerName(resultSet.getString("buyer_name"));
                order.setAddress(resultSet.getString("address"));
                order.setBuyerCellNumber(resultSet.getString("buyer_cell_number"));


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
                    "UPDATE electronic.order SET user_id=?,comment=?,is_done=?,buyer_name=?,address=?,buyer_cell_number=? WHERE id=?");

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getComment());
            preparedStatement.setBoolean(3, order.getIsDone());
            preparedStatement.setString(4, order.getBuyerName());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setString(6, order.getBuyerCellNumber());
            preparedStatement.setInt(7, order.getId());

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

    @Override
    public int getLastInsertedId() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lastId = -1;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("SELECT last_insert_id() AS id FROM electronic.order");

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                lastId = resultSet.getInt("id");
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
        return lastId;
    }
}
