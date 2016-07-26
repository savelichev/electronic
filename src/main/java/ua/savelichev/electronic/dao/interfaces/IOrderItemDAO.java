package ua.savelichev.electronic.dao.interfaces;


import ua.savelichev.electronic.domain.entity.OrderItem;

import java.util.List;

public interface IOrderItemDAO {

    void createOrderItems(List<OrderItem> orderItems, int orderId);

    OrderItem getOrderItemById(int orderItemId);

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    void updateOrderItem(OrderItem orderItem);

    void deleteOrderItemById(int orderItemId);

    void deleteOrderItemsByOrderId(int orderId);


}
