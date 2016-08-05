package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IOrderItemDAO;
import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.entity.OrderItem;
import ua.savelichev.electronic.domain.entity.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OrderItemDAOTest {

    IConnectionFactory connectionFactory;
    IOrderItemDAO orderItemDAO;

    List<OrderItem> orderItems;

    @Before
    public void init() {
        connectionFactory = ConnectionFactoryForTest.getInstance();
        orderItemDAO = new OrderItemDAO(connectionFactory);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(1);
        orderItem.setProductArticle(141);
        orderItem.setPrice(100);
        orderItem.setAmount(2);
        orderItem.setTitle("Title");

        orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        TableCleaner tableCleaner = new TableCleaner();
        tableCleaner.cleanTables("orders", "user", "order_item");
        createOrderAnDUser();
        orderItemDAO.createOrderItems(orderItems, 1);
    }

    @Test
    public void testCreateOrderItems() throws Exception {
        assertEquals(1, orderItemDAO.getOrderItemsByOrderId(1).size());
    }

    @Test
    public void testGetOrderItemById() throws Exception {
        int id = orderItemDAO.getOrderItemsByOrderId(1).get(0).getId();
        assertEquals(141, orderItemDAO.getOrderItemById(id).getProductArticle());
    }

    @Test
    public void testGetOrderItemsByOrderId() throws Exception {
        assertEquals(1, orderItemDAO.getOrderItemsByOrderId(1).size());
    }

    @Test
    public void testUpdateOrderItem() throws Exception {
        int id = orderItemDAO.getOrderItemsByOrderId(1).get(0).getId();

        orderItems.get(0).setId(id);
        orderItems.get(0).setTitle("test");

        orderItemDAO.updateOrderItem(orderItems.get(0));
        assertEquals("test", orderItemDAO.getOrderItemsByOrderId(1).get(0).getTitle());
    }

    @Test
    public void testDeleteOrderItemById() throws Exception {
        int id = orderItemDAO.getOrderItemsByOrderId(1).get(0).getId();

        orderItemDAO.deleteOrderItemById(id);
        assertEquals(0, orderItemDAO.getOrderItemsByOrderId(1).size());

    }

    @Test
    public void testDeleteOrderItemsByOrderId() throws Exception {
        orderItemDAO.deleteOrderItemsByOrderId(1);
        assertEquals(0, orderItemDAO.getOrderItemsByOrderId(1).size());
    }


    private void createOrderAnDUser() {
        User user = new User("test@gmail.com");
        user.setId(1);
        user.setLogin("login");
        user.setPassword("password");

        Order order = new Order();
        order.setId(1);
        order.setUserId(1);


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO user(id,email,login,password) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();

            preparedStatement = connection.prepareStatement("INSERT INTO orders(id,user_id) VALUES(?,?)");
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getUserId());
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
