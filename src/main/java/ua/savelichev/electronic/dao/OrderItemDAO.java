package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IOrderItemDAO;
import ua.savelichev.electronic.domain.entity.OrderItem;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderItemDAO implements IOrderItemDAO {

    private IConnectionFactory connectionFactory;

    private static final Logger log = Logger.getLogger(OrderItemDAO.class);

    private ResourceBundle bundle = ResourceBundle.getBundle("SQLQueries");

    public OrderItemDAO(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Inserts into table "order_item" order items
     *
     * @param orderItems list of OrderItem for insert
     * @param orderId    relevant order for items in list
     */
    @Override
    public void createOrderItems(List<OrderItem> orderItems, int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);
            for (OrderItem orderItem : orderItems) {

                preparedStatement = connection.prepareStatement(bundle.getString("CREATE_ORDER_ITEM"));

                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, orderItem.getProductArticle());
                preparedStatement.setInt(3, orderItem.getPrice());
                preparedStatement.setInt(4, orderItem.getAmount());
                preparedStatement.setString(5, orderItem.getTitle());

                preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            }
            connection.commit();
            for (OrderItem orderItem : orderItems) {
                log.debug("Order item for order with id: " + orderId + " was inserted: " + orderItem);
            }
            connection.setAutoCommit(true);

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
     * Returns OrderItem from database relevant input id
     *
     * @param id - id of Order Item in table
     * @return OrderItem
     */
    @Override
    public OrderItem getOrderItemById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OrderItem orderItem = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("GET_ORDER_ITEM_BY_ID"));

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
        return orderItem;
    }

    /**
     * Returns all OrderItems which relevant input Order's id
     *
     * @param orderId id of Order
     * @return List of OrderItems
     */
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<OrderItem> orderItems = null;
        OrderItem orderItem = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("SELECT_ORDER_ITEMS_BY_ORDER_ID"));

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
            log.debug("Got list order items fo order id: " + orderId);

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
        return orderItems;
    }

    /**
     * Updates OrderItem in database if found
     *
     * @param orderItem OrderItem with new parameters
     */
    @Override
    public void updateOrderItem(OrderItem orderItem) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("UPDATE_ORDER_ITEM"));

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getProductArticle());
            preparedStatement.setInt(3, orderItem.getPrice());
            preparedStatement.setInt(4, orderItem.getAmount());
            preparedStatement.setString(5, orderItem.getTitle());
            preparedStatement.setInt(6, orderItem.getId());

            preparedStatement.executeUpdate();
            log.debug("Updated order item: " + orderItem);

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
     * Delete row of OrderItem in table "order_item" by id.
     *
     * @param orderItemId
     */
    @Override
    public void deleteOrderItemById(int orderItemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("DELETE_ORDER_ITEM_BY_ID"));

            preparedStatement.setInt(1, orderItemId);

            preparedStatement.executeUpdate();
            log.debug("Deleted order item with id: " + orderItemId);
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
     * Delete all rows which references on input order_id
     *
     * @param orderId
     */
    @Override
    public void deleteOrderItemsByOrderId(int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(bundle.getString("DELETE_ORDER_ITEMS_BY_ORDER_ID"));

            preparedStatement.setInt(1, orderId);

            preparedStatement.executeUpdate();
            log.debug("Deleted order items with order id: " + orderId);
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
