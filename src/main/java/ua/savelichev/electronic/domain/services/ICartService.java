package ua.savelichev.electronic.domain.services;


import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.entity.interfaces.ICart;

public interface ICartService {

    /**
     * Adds CartItem to exist ICart and creates new ICart, if no ICart in cartFromSession.
     *
     * @param cartFromSession value of session attribute "cart".
     * @param product  product for adding.
     * @return ICart
     */
    ICart addProduct(ICart cartFromSession, Product product);

    /**
     * Removes Product from Cart by article
     *
     * @param cartFromSession Cart from session
     * @param product product for removing.
     * @return Cart without Product with relevant article
     */
    ICart removeProduct(ICart cartFromSession, Product product);


    /**
     * Decrease Product amount in cart per one unit if it bigger then "1".
     *
     * @param cartFromSession target cart
     * @param product  product for decreasing.
     * @return Cart
     */
    ICart decreaseProductAmount(ICart cartFromSession, Product product);


}
