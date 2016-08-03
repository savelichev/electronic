package ua.savelichev.electronic.domain.services;


import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IOrderDAO;
import ua.savelichev.electronic.domain.entity.*;
import ua.savelichev.electronic.domain.entity.interfaces.IOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import static org.junit.Assert.assertEquals;

public class OrderServiceTest {


    IOrderDAO mockOrderDAO;
    IDAOFactory mockDaoFactory;
    IOrderService orderService;

    @Before
    public void init() {

        mockOrderDAO = mock(IOrderDAO.class);
        mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getOrderDAO()).thenReturn(mockOrderDAO);
        orderService = new OrderService(mockDaoFactory);

    }

    @Test
    public void testBuildOrderTemplate() {

        //Creation of valid Order template what expected in return of test method
        OrderItem mockValidOrderItem = mock(OrderItem.class);
        when(mockValidOrderItem.getTitle()).thenReturn("Producer Model");
        when(mockValidOrderItem.getProductArticle()).thenReturn(1234);
        when(mockValidOrderItem.getPrice()).thenReturn(100);
        when(mockValidOrderItem.getAmount()).thenReturn(5);
        when(mockValidOrderItem.getItemCost()).thenReturn(500);

        List<OrderItem> validOrderItems = new ArrayList<>();
        validOrderItems.add(mockValidOrderItem);

        IOrder mockValidOrderTemplate = mock(IOrder.class);
        when(mockValidOrderTemplate.getUserId()).thenReturn(1);
        when(mockValidOrderTemplate.getAddress()).thenReturn("Kiev");
        when(mockValidOrderTemplate.getOrderCost()).thenReturn(500);
        when(mockValidOrderTemplate.getComment()).thenReturn("");
        when(mockValidOrderTemplate.getIsDone()).thenReturn(false);
        when(mockValidOrderTemplate.getOrderItems()).thenReturn(validOrderItems);

        //Creation of mock Cart
        Product mockProduct = mock(Product.class);
        when(mockProduct.getProducer()).thenReturn("Producer");
        when(mockProduct.getModel()).thenReturn("Model");
        when(mockProduct.getArticle()).thenReturn(1234);
        when(mockProduct.getPrice()).thenReturn(100);

        CartItem mockCartItem = mock(CartItem.class);
        when(mockCartItem.getAmount()).thenReturn(5);
        when(mockCartItem.getCartItemCost()).thenReturn(500);
        when(mockCartItem.getProduct()).thenReturn(mockProduct);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(mockCartItem);

        Cart mockCart = mock(Cart.class);
        when(mockCart.getCartCost()).thenReturn(500);
        when(mockCart.getCartItems()).thenReturn(new ArrayList<CartItem>());
        when(mockCart.getCartItems()).thenReturn(cartItems);

        //Creation of mock User
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        when(mockUser.getAddress()).thenReturn("Kiev");

        IOrder receivedOrderTemplate = orderService.buildOrderTemplate(mockUser, mockCart);


        assertEquals(mockValidOrderTemplate.getUserId(), receivedOrderTemplate.getUserId());
        assertEquals(mockValidOrderTemplate.getAddress(), receivedOrderTemplate.getAddress());
        assertEquals(mockValidOrderTemplate.getOrderCost(), receivedOrderTemplate.getOrderCost());
        assertEquals(mockValidOrderTemplate.getComment(), receivedOrderTemplate.getComment());
        assertEquals(mockValidOrderTemplate.getIsDone(), receivedOrderTemplate.getIsDone());

        List<OrderItem> receivedOrderItems = receivedOrderTemplate.getOrderItems();
        OrderItem receivedOrderItem = receivedOrderItems.get(0);

        assertEquals(mockValidOrderItem.getTitle(), receivedOrderItem.getTitle());
        assertEquals(mockValidOrderItem.getProductArticle(), receivedOrderItem.getProductArticle());
        assertEquals(mockValidOrderItem.getPrice(), receivedOrderItem.getPrice());
        assertEquals(mockValidOrderItem.getAmount(), receivedOrderItem.getAmount());
        assertEquals(mockValidOrderItem.getItemCost(), receivedOrderItem.getItemCost());
    }

    @Test
    public void testApproveOrder() {

        IOrder order = mock(IOrder.class);
        String comment = "comment";
        String address = "Kiev";
        String buyerName = "buyerName";
        String buyerCellNumber = "0631234567";

//        IOrderService mockOrderService = mock(IOrderService.class);

        orderService.approveOrder(order, comment, address, buyerName, buyerCellNumber);
    }


    @Test
    public void testApproveOrderForNullOrder() {

        IOrder order = null;
        String comment = "comment";
        String address = "Kiev";
        String buyerName = "buyerName";
        String buyerCellNumber = "0631234567";

        orderService.approveOrder(order, comment, address, buyerName, buyerCellNumber);
    }

    @Test
    public void testCreateOrder() {
        IOrder mockOrder = mock(IOrder.class);
        orderService.createOrder(mockOrder);
    }

    @Test
    public void testCreateOrderForNullOrder() {
        IOrder order = null;
        orderService.createOrder(order);
    }

    @Test
    public void testGetUserOrders() {
        List<IOrder> orders = new ArrayList<>();
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        when(mockOrderDAO.getOrdersByUserId(anyInt())).thenReturn(orders);
        assertEquals(orders, orderService.getUserOrders(mockUser));

    }

    @Test
    public void testGetUserOrdersForNullUser() {
        List<IOrder> orders = new ArrayList<>();
        User user = null;

        when(mockOrderDAO.getOrdersByUserId(anyInt())).thenReturn(orders);
        assertNull(orderService.getUserOrders(user));

    }

}




















