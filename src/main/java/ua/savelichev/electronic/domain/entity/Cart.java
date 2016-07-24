package ua.savelichev.electronic.domain.entity;


import java.util.ArrayList;
import java.util.List;

public class Cart implements ICart {

    private List<CartItem> cartItems = new ArrayList<>();

    private int cartCost;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getCartCost() {
        return cartCost;
    }

    public void setCartCost(int cartCost) {
        this.cartCost = cartCost;
    }

    /**
     * Adds new CartItem if it's not exist, else invokes
     * {@link ua.savelichev.electronic.domain.entity.Cart#increaseCartItemAmount(CartItem)}
     */
    @Override
    public void addCartItem(CartItem cartItem) {
        if (!cartItems.contains(cartItem)) {
            cartItems.add(cartItem);
        } else increaseCartItemAmount(cartItem);
        calculateCartCost();
    }

    /**
     * Increase CartItem amount per one unit.
     */
    private void increaseCartItemAmount(CartItem cartItem) {
        CartItem tempCartItem = cartItems.get(cartItems.indexOf(cartItem));
        tempCartItem.setAmount(tempCartItem.getAmount() + 1);
        calculateCartCost();
    }

    /**
     * Decrease CartItem amount per one unit if it bigger then "1".
     * It means that amount could not be less then "1".
     * If you need to remove CartItem, invoke method
     * {@link ua.savelichev.electronic.domain.entity.Cart#removeCartItem(CartItem)}
     *
     * @param cartItem - target for decreasing
     * @see ua.savelichev.electronic.domain.entity.Cart#removeCartItem(CartItem)
     */
    @Override
    public void decreaseCartItemAmount(CartItem cartItem) {
        CartItem tempCartItem = cartItems.get(cartItems.indexOf(cartItem));
        if (tempCartItem.getAmount() > 1) {
            tempCartItem.setAmount(tempCartItem.getAmount() - 1);

        }
        calculateCartCost();
    }

    /**
     * Removes CartItem from Cart
     */
    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        calculateCartCost();
    }


    private void calculateCartCost() {
        int tempCartCost = 0;
        for (CartItem cartItem : cartItems) {
            tempCartCost += cartItem.getCartItemCost();
        }
        cartCost = tempCartCost;
    }
}