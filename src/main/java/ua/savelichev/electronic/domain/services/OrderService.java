package ua.savelichev.electronic.domain.services;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IOrderDAO;
import ua.savelichev.electronic.domain.entity.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    private static final Logger log = Logger.getLogger(OrderService.class);

    private IDAOFactory daoFactory;

    public OrderService(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Builds Order Template.
     * Takes User and Cart and converse them to Order.
     * This Order template will be saves after approving.
     *
     * @param user owner of Cart.
     * @param cart Cart with List of CartItem.
     * @return template of order which needs to be approved by User.
     */
    public Order buildOrderTemplate(User user, Cart cart) {
        Order order = new Order();

        order.setUserId(user.getId());
        order.setAddress(user.getAddress());
        order.setOrderCost(cart.getCartCost());
        order.setComment("");
        order.setIsDone(false);

        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem orderItem;
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
        log.debug("Order template was built: " + order);
        return order;
    }

    /**
     * Completes Order by Order template.
     * Includes approved information from user to Order
     *
     * @param order           Order template to approve
     * @param comment         users comment to order
     * @param address         delivery address
     * @param buyerName       buyer name
     * @param buyerCellNumber buyer cell number
     */
    public void approveOrder(Order order, String comment, String address, String buyerName, String buyerCellNumber) {
        order.setComment(comment);
        order.setAddress(address);
        order.setBuyerName(buyerName);
        order.setBuyerCellNumber(buyerCellNumber);
        log.debug("Try to approve Order: " + order);
        createOrder(order);
    }

    /**
     * Saves Order to database
     *
     * @param order Order to save
     */
    @Override
    public void createOrder(Order order) {
        IOrderDAO orderDAO = daoFactory.getOrderDAO();
        orderDAO.createOrder(order);
        log.debug("Order created: " + order);
    }

    /**
     * Takes all User Orders from database
     *
     * @param user User fo search
     * @return List of User
     */
    @Override
    public List<Order> getUserOrders(User user) {
        IOrderDAO orderDAO = daoFactory.getOrderDAO();
        int userId = user.getId();
        List<Order> orders = orderDAO.getOrdersByUserId(userId);
        log.debug("Got orders of user: " + user.getEmail());
        return orders;
    }
}
