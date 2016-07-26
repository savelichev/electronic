package ua.savelichev.electronic.domain.services;


import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.CartItem;
import ua.savelichev.electronic.domain.entity.ICart;
import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.services.product.ProductUtils;

public class CartService implements ICartService {

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
            cart = (ICart) cartFromSession;
        } else {
            cart = new Cart();
        }

        cart.addCartItem(cartItem);

        return cart;
    }


    @Override
    public ICart removeProduct(ICart cartFromSession, String productArticle) {

        Product product = ProductUtils.getProductByArticle(Integer.valueOf(productArticle));

        if (product != null) {
            cartItem = new CartItem(product);
        }

        if (cartFromSession != null) {
            cart = (ICart) cartFromSession;
            cart.removeCartItem(cartItem);
        }

        if (cart != null) {
            ;
        }

        return cart;
    }

//    @Override
//    public ICart increaseProductAmount(ICart cartFromSession, String productArticle) {
//        Product product = ProductUtils.getProductByArticle(Integer.valueOf(productArticle));
//
//        if (product != null) {
//            cartItem = new CartItem(product);
//        }
//
//        if (cartFromSession != null && cartFromSession instanceof ICart) {
//            cart = (ICart) cartFromSession;
//            cart.increaseCartItemAmount(cartItem);
//        }
//        return cart;
//    }


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
