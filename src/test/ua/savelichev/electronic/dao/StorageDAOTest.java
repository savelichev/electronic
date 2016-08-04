package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;
import ua.savelichev.electronic.domain.entity.StoragePosition;

import static org.junit.Assert.assertEquals;


public class StorageDAOTest {

    IConnectionFactory connectionFactory;
    IStorageDAO storageDAO;
    TableCleaner tableCleaner;
    StoragePosition storagePosition;

    @Before
    public void init() {
        connectionFactory = ConnectionFactoryForTest.getInstance();
        storageDAO = new StorageDAO(connectionFactory);
        tableCleaner = new TableCleaner();
        storagePosition = new StoragePosition(141, 10);
        tableCleaner.cleanTables("storage");
    }

    @Test
    public void testCreateStoragePosition() throws Exception {

        storageDAO.createStoragePosition(storagePosition);

        assertEquals(1, storageDAO.getAllStoragePositions().size());
    }

    @Test
    public void testGetStoragePositionByArticle() throws Exception {

        storageDAO.createStoragePosition(storagePosition);

        StoragePosition storagePositionFromDB = storageDAO.getStoragePositionByArticle(storagePosition.getArticle());

        assertEquals(storagePosition, storagePositionFromDB);

    }

    @Test
    public void testUpdateStoragePosition() throws Exception {

        storageDAO.createStoragePosition(storagePosition);

        storagePosition.setAmount(100);

        storageDAO.updateStoragePosition(storagePosition);

        StoragePosition storagePositionFromDB = storageDAO.getStoragePositionByArticle(141);

        assertEquals(100, storagePositionFromDB.getAmount());
    }

    @Test
    public void testGetAllStoragePositions() throws Exception {
        storageDAO.createStoragePosition(storagePosition);
        storageDAO.createStoragePosition(storagePosition);
        assertEquals(2, storageDAO.getAllStoragePositions().size());
    }
}