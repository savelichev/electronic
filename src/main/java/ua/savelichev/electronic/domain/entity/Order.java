package ua.savelichev.electronic.domain.entity;


import ua.savelichev.electronic.domain.entity.interfaces.IOrder;

import java.util.List;

/**
 * Class contains order parameters
 */
public class Order implements IOrder {

    /**
     * Order identifier
     */
    private int id;

    /**
     * Order owner identifier
     */
    private int userId;

    /**
     * List of order items
     */
    private List<OrderItem> orderItems;

    /**
     * Delivery address
     */
    private String address;

    /**
     * Order total cost
     */
    private int orderCost;

    /**
     * Owner comment to order
     */
    private String comment;

    /**
     * Order condition flag
     */
    private boolean isDone;

    /**
     * Buyer name
     */
    private String buyerName;

    /**
     * Buyer contact cell number
     */
    private String buyerCellNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerCellNumber() {
        return buyerCellNumber;
    }

    public void setBuyerCellNumber(String buyerCellNumber) {
        this.buyerCellNumber = buyerCellNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderItems=" + orderItems +
                ", address='" + address + '\'' +
                ", orderCost=" + orderCost +
                ", comment='" + comment + '\'' +
                ", isDone=" + isDone +
                ", buyerName='" + buyerName + '\'' +
                ", buyerCellNumber='" + buyerCellNumber + '\'' +
                '}';
    }
}
