package ua.savelichev.electronic.dao.interfaces;

public interface IStorageDAO {

    void createPosition(int article,int amount);

    int getPositionAmountByArticle(int article);

    void updatePositionAmountByArticle(int article, int amount);


}
