package ua.savelichev.electronic.domain.entity.interfaces;

import ua.savelichev.electronic.domain.entity.CartItem;

public interface ICart {

    /**
     * Adds CartItem to Cart
     * If current CartItem exist in Cart it increases CartItem per one unit.
     *
     * @param cartItem CartItem to adding.
     */
    void addCartItem(CartItem cartItem);

    /**
     * Decreases CartItem amount per one unit if it bigger then "1".
     * It means that amount could not be less then "1".
     * If it needs to remove CartItem, invoke the method
     * {@link ua.savelichev.electronic.domain.entity.Cart#removeCartItem(CartItem)}
     *
     * @param cartItem - target for decreasing
     * @see ua.savelichev.electronic.domain.entity.Cart#removeCartItem(CartItem)
     */
    void decreaseCartItemAmount(CartItem cartItem);


    /**
     * Removes CartItem from Cart
     *
     * @param cartItem CartItem for removing
     */
    void removeCartItem(CartItem cartItem);


}
