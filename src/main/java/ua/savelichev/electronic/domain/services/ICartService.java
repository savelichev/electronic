package ua.savelichev.electronic.domain.services;


import ua.savelichev.electronic.domain.entity.ICart;

public interface ICartService {

    /**
     * Adds CartItem to exist ICart and creates new ICart, if no ICart in cartFromSession.
     *
     * @param cartFromSession value of session attribute "cart".
     * @param productArticle  value of request attribute "productArticle".
     * @return ICart
     */
    ICart addProduct(ICart cartFromSession, String productArticle);

    /**
     * Removes Product from Cart by article
     *
     * @param cartFromSession Cart from session
     * @param productArticle  article of Product for removing
     * @return Cart without Product with relevant article
     */
    ICart removeProduct(ICart cartFromSession, String productArticle);


    /**
     * Decrease Product amount in cart per one unit if it bigger then "1".
     * @param cartFromSession target cart
     * @param productArticle target product's article
     * @return
     */
        ICart decreaseProductAmount(ICart cartFromSession, String productArticle);


}
