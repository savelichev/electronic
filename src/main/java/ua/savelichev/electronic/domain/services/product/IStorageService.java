package ua.savelichev.electronic.domain.services.product;


public interface IStorageService {

    int getProductAmountByArticle(int article);

    void changeProductAmount(int article, int amount);

    void createStoragePosition(int article, int amount);

}
