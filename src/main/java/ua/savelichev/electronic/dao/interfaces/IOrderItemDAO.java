package ua.savelichev.electronic.dao.interfaces;


import ua.savelichev.electronic.domain.entity.OrderItem;

import java.util.List;

public interface IOrderItemDAO {

    /**
     * Inserts rows into the table "order_item"
     * for universal using works with List
     *
     * @param orderItems target for inserting
     * @param orderId    relevant Order for OrderItems
     */
    void createOrderItems(List<OrderItem> orderItems, int orderId);

    /**
     * Selects row from the table "order_item" by field "id"
     *
     * @param orderItemId target id
     * @return OrderItem object
     */
    OrderItem getOrderItemById(int orderItemId);

    /**
     * Selects rows from the table "order_item" by field "order_id"
     *
     * @param orderId target order_id
     * @return List of OrderItem objects
     */
    List<OrderItem> getOrderItemsByOrderId(int orderId);

    /**
     * Updates relevant row in table "order_item"
     *
     * @param orderItem contains new parameters for relevant row
     */
    void updateOrderItem(OrderItem orderItem);

    /**
     * Deletes row from the table "order_item" by field "id"
     *
     * @param orderItemId target row id
     */
    void deleteOrderItemById(int orderItemId);

    /**
     * Deletes rows from table "order_item" by field "order_id"
     *
     * @param orderId target rows order_id
     */
    void deleteOrderItemsByOrderId(int orderId);


}
