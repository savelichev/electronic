package ua.savelichev.electronic.dao;

import ua.savelichev.electronic.dao.interfaces.IOrderItemDAO;
import ua.savelichev.electronic.domain.entity.OrderItem;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDAOImpl implements IOrderItemDAO {

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();


    @Override
    public void createOrderItem(OrderItem orderItem) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO electronic.order_item (order_id, product_article,price,amount,title) VALUES(?,?,?,?,?)");

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getProductArticle());
            preparedStatement.setInt(3, orderItem.getPrice());
            preparedStatement.setInt(4, orderItem.getAmount());
            preparedStatement.setString(5, orderItem.getTitle());

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
    public OrderItem getOrderItemById(int orderItemId) {
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return null;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {

    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {

    }
}
