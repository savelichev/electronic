package ua.savelichev.electronic.dao.interfaces;

public interface IStorageDAO {

    /**
     * Inserts new row into the table "storage"
     *
     * @param article Product article
     * @param amount  amount of Product
     */
    void createPosition(int article, int amount);

    /**
     * Selects row from the table "storage" by field "article"
     *
     * @param article target row article value
     * @return value of field "amount"
     */
    int getPositionAmountByArticle(int article);

    /**
     * Updates row in the table "order"
     *
     * @param article target row article value
     * @param amount  new value for field "amount"
     */
    void updatePositionAmountByArticle(int article, int amount);


}
