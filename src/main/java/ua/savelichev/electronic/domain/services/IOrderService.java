package ua.savelichev.electronic.domain.services;


import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.interfaces.IOrder;
import ua.savelichev.electronic.domain.entity.User;

import java.util.List;

public interface IOrderService {

    /**
     * Builds Order Template.
     * Takes User and Cart and converse them to Order.
     * This Order template will be saves after approving.
     *
     * @param user owner of Cart.
     * @param cart Cart with List of CartItem.
     * @return template of order which needs to be approved by User.
     */
    IOrder buildOrderTemplate(User user, Cart cart);

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
    void approveOrder(IOrder order, String comment, String address, String buyerName, String buyerCellNumber);

    /**
     * Saves Order to database
     *
     * @param order Order to save
     */
    void createOrder(IOrder order);

    /**
     * Takes all User Orders from database
     *
     * @param user User fo search
     * @return List of User
     */
    List<IOrder> getUserOrders(User user);

}
