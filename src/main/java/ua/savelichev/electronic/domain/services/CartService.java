package ua.savelichev.electronic.domain.services;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.CartItem;
import ua.savelichev.electronic.domain.entity.interfaces.ICart;
import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.services.product.ProductUtils;

public class CartService implements ICartService {

    private static final Logger log = Logger.getLogger(CartService.class);

    /**
     * Adds CartItem to exist ICart and creates new ICart, if no ICart in cartFromSession.
     *
     * @param cartFromSession value of session attribute "cart".
     * @param product         product for adding.
     * @return ICart
     */
    @Override
    public ICart addProduct(ICart cartFromSession, Product product) {

        ICart cart;
        CartItem cartItem = null;

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = cartFromSession;
        } else {
            cart = new Cart();
        }

        cart.addCartItem(cartItem);
        log.debug("Product was added. Product article: " + product);
        return cart;
    }


    /**
     * Removes Product from Cart by article
     *
     * @param cartFromSession Cart from session
     * @param product         Product for removing
     * @return Cart without Product with relevant article
     */
    @Override
    public ICart removeProduct(ICart cartFromSession, Product product) {

        ICart cart = null;
        CartItem cartItem = null;

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = cartFromSession;
            cart.removeCartItem(cartItem);
        }
        log.debug("Product was removed. Product article: " + product);
        return cart;
    }


    /**
     * Decrease Product amount in cart per one unit if it bigger then "1".
     *
     * @param cartFromSession target cart
     * @param product         Product for decreasing
     * @return Cart with decreased amount of Product
     */
    @Override
    public ICart decreaseProductAmount(ICart cartFromSession, Product product) {

        ICart cart = null;
        CartItem cartItem = null;

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = cartFromSession;
            cart.decreaseCartItemAmount(cartItem);
        }

        return cart;
    }


}
