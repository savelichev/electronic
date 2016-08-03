package ua.savelichev.electronic.domain.entity.interfaces;

import ua.savelichev.electronic.domain.entity.OrderItem;

import java.util.List;

public interface IOrder {

    int getId();

    void setId(int id);

    int getUserId();

    void setUserId(int userId);

    List<OrderItem> getOrderItems();

    void setOrderItems(List<OrderItem> orderItems);

    String getAddress();

    void setAddress(String address);

    int getOrderCost();

    void setOrderCost(int orderCost);

    String getComment();

    void setComment(String comment);

    boolean getIsDone();

    void setIsDone(boolean done);

    String getBuyerName();

    void setBuyerName(String buyerName);

    String getBuyerCellNumber();

    void setBuyerCellNumber(String buyerCellNumber);
}
