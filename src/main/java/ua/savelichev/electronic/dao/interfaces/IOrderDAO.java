package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.interfaces.IOrder;

import java.util.List;

public interface IOrderDAO {

    /**
     * Inserts row into the table "order"
     *
     * @param order target for inserting
     */
    void createOrder(IOrder order);

    /**
     * Selects row from the table "order"
     *
     * @param id relevant row id
     * @return Order object
     */
    IOrder getOrderById(int id);

    /**
     * Selects rows from the table "order" by field "user_id"
     *
     * @param userId relevant rows user_id
     * @return List of Order objects
     */
    List<IOrder> getOrdersByUserId(int userId);


    /**
     * Selects all rows from the table "order"
     * @return List of Order objects
     */
    List<IOrder> getAllOrders();

    /**
     *  Updates relevant row in table "order"
     * @param order contains new parameters for relevant row
     */
    void updateOrder(IOrder order);

    /**
     * Deletes row in table "order" by field "id"
     * @param order target row
     */
    void deleteOrder(IOrder order);


}
