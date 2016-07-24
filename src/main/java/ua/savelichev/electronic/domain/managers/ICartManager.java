package ua.savelichev.electronic.domain.managers;


import ua.savelichev.electronic.domain.entity.CartItem;
import ua.savelichev.electronic.domain.entity.ICart;

public interface ICartManager {

    ICart addProduct(ICart cartFromSession, String productArticle);

    ICart removeProduct(ICart cartFromSession, String productArticle);

//    ICart increaseProductAmount(ICart cartFromSession, String productArticle);

    ICart decreaseProductAmount(ICart cartFromSession, String productArticle);


}
