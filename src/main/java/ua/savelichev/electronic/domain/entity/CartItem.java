package ua.savelichev.electronic.domain.entity;

public class CartItem {

    private Product product;

    private int amount;

    private int cartItemCost;

    public CartItem() {
    }

    public CartItem(Product product) {
        this.product = product;
        this.amount = 1;
        calculateCartItemCost();
    }

    public CartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
        calculateCartItemCost();
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        calculateCartItemCost();
    }

    public int getCartItemCost() {
        return cartItemCost;
    }

    private void calculateItemCost() {
        cartItemCost = product.getPrice() * amount;
    }


    /**
     * Cart items are equals if they contains the same product
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return product.equals(cartItem.product);

    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }


    private void calculateCartItemCost() {
        cartItemCost = product.getPrice() * amount;
    }
}
