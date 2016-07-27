package ua.savelichev.electronic.domain.services;


import ua.savelichev.electronic.domain.entity.ICart;

public interface ICartService {

    ICart addProduct(ICart cartFromSession, String productArticle);

    ICart removeProduct(ICart cartFromSession, String productArticle);

    ICart decreaseProductAmount(ICart cartFromSession, String productArticle);


}
