package ua.savelichev.electronic.domain.managers;


import ua.savelichev.electronic.dao.OrderDAO;
import ua.savelichev.electronic.dao.OrderItemDAO;
import ua.savelichev.electronic.dao.interfaces.IOrderDAO;
import ua.savelichev.electronic.dao.interfaces.IOrderItemDAO;
import ua.savelichev.electronic.domain.entity.*;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements IOrderManager {


    public Order buildOrderTemplate(User user, Cart cart) {

        Order order = new Order();

        order.setUserId(user.getId());

        order.setAddress(user.getAddress());

        order.setOrderCost(cart.getCartCost());

        order.setComment("");

        order.setIsDone(false);

        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem orderItem = null;

        for (CartItem cartItem : cart.getCartItems()) {

            Product product = cartItem.getProduct();

            orderItem = new OrderItem();

            orderItem.setTitle(product.getProducer() + " " + product.getModel());

            orderItem.setProductArticle(product.getArticle());

            orderItem.setPrice(product.getPrice());

            orderItem.setAmount(cartItem.getAmount());

            orderItem.setItemCost(cartItem.getCartItemCost());

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);


        return order;
    }

    public void approveOrder(Order order, String comment, String address, String buyerName, String buyerCellNumber) {

        order.setComment(comment);
        order.setAddress(address);
        order.setBuyerName(buyerName);
        order.setBuyerCellNumber(buyerCellNumber);

        saveOrder(order);
    }

    @Override
    public void saveOrder(Order order) {

        IOrderDAO orderDAO = new OrderDAO();

        IOrderItemDAO orderItemDAO = new OrderItemDAO();

        orderDAO.createOrder(order);

        int orderId = orderDAO.getLastInsertedId();

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrderId(orderId);
            orderItemDAO.createOrderItem(orderItem);
        }


    }

    @Override
    public List<Order> getUserOrders(User user) {
        OrderDAO orderDAO=new OrderDAO();
        int userId=user.getId();
        return orderDAO.getOrdersByUserId(userId);
    }
}
