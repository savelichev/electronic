package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.StoragePosition;

import java.util.List;

public interface IStorageDAO {

    /**
     * Inserts new row into the table "storage"
     *
     * @param storagePosition new storage position
     */
    void createStoragePosition(StoragePosition storagePosition);

    /**
     * Selects row from the table "storage" by field "article"
     *
     * @param article target row article value
     * @return value of field "amount"
     */
    StoragePosition getStoragePositionByArticle(int article);

    /**
     * Updates row in the table "order"
     *
     * @param storagePosition position for update
     */
    void updateStoragePosition(StoragePosition storagePosition);

    List<StoragePosition> getAllStoragePositions();

}
