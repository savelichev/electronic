package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.entity.OrderItem;
import ua.savelichev.electronic.domain.entity.User;
import ua.savelichev.electronic.domain.entity.interfaces.IOrder;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OrderDAOTest {

    private IConnectionFactory connectionFactory;
    private OrderDAO orderDAO;
    Order order;

    @Before
    public void init() {
        connectionFactory = ConnectionFactoryForTest.getInstance();
        orderDAO = new OrderDAO(connectionFactory);
        order = new Order();
        order.setUserId(1);
        order.setComment("comment");
        order.setIsDone(false);
        order.setBuyerName("BuyerName");
        order.setAddress("Address");
        order.setBuyerCellNumber("1234567890");

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProductArticle(141);
        orderItem1.setPrice(100);
        orderItem1.setAmount(3);
        orderItem1.setTitle("Title1");

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProductArticle(142);
        orderItem2.setPrice(200);
        orderItem2.setAmount(5);
        orderItem2.setTitle("Title2");

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);

        TableCleaner tableCleaner = new TableCleaner();
        tableCleaner.cleanTables("orders", "user");

        createUser();
        orderDAO.createOrder(order);


    }

    @Test
    public void testCreateOrder() throws Exception {

        List<IOrder> orders = orderDAO.getAllOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void testGetOrdersByUserId() throws Exception {

        assertEquals(order.getUserId(), (orderDAO.getOrdersByUserId(1)).get(0).getUserId());

    }

    @Test
    public void testGetAllOrders() throws Exception {

        assertEquals(1, orderDAO.getAllOrders().size());
    }

    @Test
    public void testUpdateOrder() throws Exception {

        int id= orderDAO.getAllOrders().get(0).getId();
        order.setId(id);
        order.setBuyerName("test");

        orderDAO.updateOrder(order);

        assertEquals("test", orderDAO.getAllOrders().get(0).getBuyerName());
    }

    @Test
    public void testDeleteOrder() throws Exception {

        int id= orderDAO.getAllOrders().get(0).getId();
        order.setId(id);
        orderDAO.deleteOrder(order);

        assertEquals(0, orderDAO.getAllOrders().size());
    }

    @Test
    public void testGetOrderById() throws Exception {
        int id= orderDAO.getAllOrders().get(0).getId();
        assertEquals(order.getAddress(),orderDAO.getOrderById(id).getAddress());
    }


    private void createUser() {
        User user = new User("test@gmail.com");
        user.setId(1);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setCellNumber("+380631234567");
        user.setLogin("Login");
        user.setPassword("Password");
        user.setAddress("Address");
        user.setRole("test");
        user.setBlocked(false);


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO user(id,firstname,lastname,cell_number,email,login,password,address)" +
                            " VALUES(?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getCellNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getAddress());

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