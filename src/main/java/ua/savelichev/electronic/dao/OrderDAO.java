package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
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
    private static final Logger log = Logger.getLogger(OrderDAO.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");

    @Override
    public void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(bundle.getString("CREATE_ORDER"));

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getComment());
            preparedStatement.setBoolean(3, order.getIsDone());
            preparedStatement.setString(4, order.getBuyerName());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setString(6, order.getBuyerCellNumber());

            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();

            ResultSet resultSet = preparedStatement.executeQuery(bundle.getString("SELECT_LAST_INSERTED_ORDER_ID"));
            int orderId = 0;
            if (resultSet.next()) {
                orderId = resultSet.getInt("id");
            }
            preparedStatement.clearParameters();

            try {
                for (OrderItem orderItem : order.getOrderItems()) {
                    preparedStatement = connection.prepareStatement(bundle.getString("CREATE_ORDER_ITEM"));

                    preparedStatement.setInt(1, orderId);
                    preparedStatement.setInt(2, orderItem.getProductArticle());
                    preparedStatement.setInt(3, orderItem.getPrice());
                    preparedStatement.setInt(4, orderItem.getAmount());
                    preparedStatement.setString(5, orderItem.getTitle());

                    preparedStatement.executeUpdate();
                    preparedStatement.clearParameters();
                }
            } catch (SQLException e) {
                log.error("Exception in creation of list order items: " + e);
                throw e;
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                log.error("Exception rollback transaction: " + ex);
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

    @Override
    public Order getOrderById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order order = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(bundle.getString("GET_ORDER_BY_ID"));

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
            preparedStatement = connection.prepareStatement(bundle.getString("GET_ORDERS_BY_USER_ID"));

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

            preparedStatement = connection.prepareStatement(bundle.getString("GET_ALL_ORDERS"));

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
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_ORDER"));

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getComment());
            preparedStatement.setBoolean(3, order.getIsDone());
            preparedStatement.setString(4, order.getBuyerName());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setString(6, order.getBuyerCellNumber());
            preparedStatement.setInt(7, order.getId());

            preparedStatement.executeUpdate();
            log.debug("Order" + order.getId() + "updated");
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
    public void deleteOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(bundle.getString("DELETE_ORDER"));
            preparedStatement.setInt(1, order.getId());

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
