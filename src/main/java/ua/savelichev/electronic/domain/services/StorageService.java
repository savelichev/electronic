package ua.savelichev.electronic.domain.services;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.StorageDAO;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;
import ua.savelichev.electronic.domain.services.product.IStorageService;

public class StorageService implements IStorageService {

    private static final Logger log = Logger.getLogger(StorageService.class);

    /**
     * Gets amount of Product at storage.
     *
     * @param article Product article
     * @return int amount of Product at storage
     */
    public int getProductAmountByArticle(int article) {
        IStorageDAO storageDAO = new StorageDAO();
        int amount = storageDAO.getPositionAmountByArticle(article);
        log.debug("Got amount:" + amount + " for article: " + article);
        return amount;
    }

    /**
     * Changes Product amount at storage.
     *
     * @param article Product article
     * @param amount  new amount
     */
    public void changeProductAmount(int article, int amount) {
        IStorageDAO storageDAO = new StorageDAO();
        log.debug("Try to change amount of product with article: " + article + " to: " + amount);
        storageDAO.updatePositionAmountByArticle(article, amount);
    }

    /**
     * Creates new storage position.
     *
     * @param article Product article of new position
     * @param amount  Product amount for new position
     */
    public void createStoragePosition(int article, int amount) {
        IStorageDAO storageDAO = new StorageDAO();
        log.debug("Try to create storage position with article: " + article + " and amount: " + amount);
        storageDAO.createPosition(article, amount);
    }
}
