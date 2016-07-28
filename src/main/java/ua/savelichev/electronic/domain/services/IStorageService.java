package ua.savelichev.electronic.domain.services;


public interface IStorageService {


    /**
     * Gets amount of Position at storage.
     *
     * @param article Product article
     * @return int amount of Product at storage
     */
    int getPositionAmountByArticle(int article);

    /**
     * Changes Position amount at storage.
     *
     * @param article Product article
     * @param amount  new amount
     */
    void changePositionAmount(int article, int amount);

    /**
     * Creates new storage position.
     *
     * @param article Product article of new position
     * @param amount  Product amount for new position
     */
    void createStoragePosition(int article, int amount);

}
