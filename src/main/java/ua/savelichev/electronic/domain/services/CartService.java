package ua.savelichev.electronic.domain.services;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.CartItem;
import ua.savelichev.electronic.domain.entity.ICart;
import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.services.product.ProductUtils;

public class CartService implements ICartService {

    private static final Logger log = Logger.getLogger(CartService.class);

    ICart cart = null;
    CartItem cartItem = null;


    /**
     * Adds CartItem to exist ICart and creates new ICart, if no ICart in cartFromSession.
     *
     * @param cartFromSession value of session attribute "cart".
     * @param productArticle  value of request attribute "productArticle".
     * @return ICart
     */
    @Override
    public ICart addProduct(ICart cartFromSession, String productArticle) {

        Product product = ProductUtils.getProductByArticle(Integer.valueOf(productArticle));

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = cartFromSession;
        } else {
            cart = new Cart();
        }

        cart.addCartItem(cartItem);
        log.debug("Product was added. Product article: " + productArticle);
        return cart;
    }


    /**
     * Removes Product from Cart by article
     *
     * @param cartFromSession Cart from session
     * @param productArticle  article of Product for removing
     * @return Cart without Product with relevant article
     */
    @Override
    public ICart removeProduct(ICart cartFromSession, String productArticle) {

        Product product = ProductUtils.getProductByArticle(Integer.valueOf(productArticle));

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = cartFromSession;
            cart.removeCartItem(cartItem);
        }
        log.debug("Product was removed. Product article: " + productArticle);
        return cart;
    }


    /**
     * Decrease Product amount in cart per one unit if it bigger then "1".
     *
     * @param cartFromSession target cart
     * @param productArticle  target product's article
     * @return
     */
    @Override
    public ICart decreaseProductAmount(ICart cartFromSession, String productArticle) {

        Product product = ProductUtils.getProductByArticle(Integer.valueOf(productArticle));

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = (ICart) cartFromSession;
            cart.decreaseCartItemAmount(cartItem);
        }

        return cart;
    }


}
