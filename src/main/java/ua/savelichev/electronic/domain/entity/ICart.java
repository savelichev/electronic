package ua.savelichev.electronic.domain.entity;

public interface ICart {

    void addCartItem(CartItem cartItem);

//    void increaseCartItemAmount(CartItem cartItem);

    /**
     * Decrease CartItem amount per one unit if it bigger then "1".
     * It means that amount could not be less then "1".
     * If you need to remove CartItem, invoke the method
     * {@link ua.savelichev.electronic.domain.entity.Cart#removeCartItem(CartItem)}
     * @see ua.savelichev.electronic.domain.entity.Cart#removeCartItem(CartItem)
     * @param cartItem - target for decreasing
     */
    void decreaseCartItemAmount(CartItem cartItem);


    void removeCartItem(CartItem cartItem);




}
