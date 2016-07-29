package ua.savelichev.electronic.domain.entity;

public class OrderItem {

    /**
     * Order item identifier.
     */
    private int id;

    /**
     * Order's id, owner of current order item.
     */
    private int orderId;

    /**
     * Order item title.
     */
    private String title;

    /**
     * Order item product article
     */
    private int productArticle;

    /**
     * Product price.
     */
    private int price;

    /**
     * Product amount.
     */
    private int amount;

    /**
     * Total cost of order item.
     */
    private int itemCost;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProductArticle() {
        return productArticle;
    }

    public void setProductArticle(int productArticle) {
        this.productArticle = productArticle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", title='" + title + '\'' +
                ", productArticle=" + productArticle +
                ", price=" + price +
                ", amount=" + amount +
                ", itemCost=" + itemCost +
                '}';
    }
}
