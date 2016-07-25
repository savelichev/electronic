package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.Order;

import java.util.List;

public interface IOrderDAO {

    void createOrder(Order order);

    Order getOrderById(int id);

    List<Order> getOrdersByUserId(int userId);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(Order order);

    int getLastInsertedId();


}
