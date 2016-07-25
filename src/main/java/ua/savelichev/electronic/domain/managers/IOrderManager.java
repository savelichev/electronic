package ua.savelichev.electronic.domain.managers;


import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.Order;
import ua.savelichev.electronic.domain.entity.User;

import java.util.List;

public interface IOrderManager {

    Order buildOrderTemplate(User user, Cart cart);

    void approveOrder(Order order, String comment, String address, String buyerName, String buyerCellNumber);

    void saveOrder(Order order);

    List<Order> getUserOrders(User user);

}
