package ua.savelichev.electronic.domain.entity;


import java.util.List;

public class Order {

    private int id;

    private int userId;

    private List<OrderItem> orderItems;

    private String address;

    private int orderCost;

    private String comment;

    private boolean isDone;

    private String buyerName;

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
