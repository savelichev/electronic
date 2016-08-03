package ua.savelichev.electronic.domain.services;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;

public class StorageService implements IStorageService {

    private static final Logger log = Logger.getLogger(StorageService.class);

    private IDAOFactory daoFactory;

    public StorageService(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Gets amount of Position at storage.
     *
     * @param article Product article
     * @return int amount of Product at storage
     */
    public int getPositionAmountByArticle(int article) {
        IStorageDAO storageDAO = daoFactory.getStorageDAO();
        int amount = storageDAO.getPositionAmountByArticle(article);
        log.debug("Got amount:" + amount + " for article: " + article);
        return amount;
    }

    /**
     * Changes Position newAmount at storage.
     *
     * @param article Product article
     * @param newAmount  new newAmount
     */
    public void changePositionAmount(int article, int newAmount) {
        IStorageDAO storageDAO = daoFactory.getStorageDAO();
        log.debug("Try to change newAmount of product with article: " + article + " to: " + newAmount);
        storageDAO.updatePositionAmountByArticle(article, newAmount);
    }

    /**
     * Creates new storage position.
     *
     * @param article Product article of new position
     * @param amount  Product amount for new position
     */
    public void createStoragePosition(int article, int amount) {
        IStorageDAO storageDAO = daoFactory.getStorageDAO();
        log.debug("Try to create storage position with article: " + article + " and amount: " + amount);
        storageDAO.createPosition(article, amount);
    }
}
