package ua.savelichev.electronic.domain.services;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;
import ua.savelichev.electronic.domain.entity.StoragePosition;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageServiceTest {

    IStorageDAO mockStorageDAO;
    IDAOFactory mockDaoFactory;
    StorageService storageService;

    @Before
    public void init() {
        mockStorageDAO = mock(IStorageDAO.class);
        mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getStorageDAO()).thenReturn(mockStorageDAO);
        storageService = new StorageService(mockDaoFactory);
    }

    @Test
    public void testGetPositionAmountByArticle() {

        int article = 12343;

        when(mockStorageDAO.getStoragePositionByArticle(anyInt())).thenReturn(new StoragePosition(article,10));

        assertEquals(10, storageService.getPositionAmountByArticle(article));
    }

    @Test
    public void testChangePositionAmount() {

        int article = 12343;
        int newAmount = 24;

        storageService.changePositionAmount(article, newAmount);
    }

    @Test
    public void testCreateStoragePosition() {

        int article = 12343;
        int newAmount = 24;

        storageService.createStoragePosition(article, newAmount);
    }
}
