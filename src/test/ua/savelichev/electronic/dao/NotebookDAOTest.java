package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.INotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.entity.StoragePosition;

import java.util.List;

import static org.junit.Assert.*;


public class NotebookDAOTest {

    IConnectionFactory connectionFactory;
    INotebookDAO notebookDAO;
    TableCleaner tableCleaner;
    Notebook notebook;

    @Before
    public void init() {
        connectionFactory = ConnectionFactoryForTest.getInstance();
        tableCleaner = new TableCleaner();

        notebookDAO = new NotebookDAO(connectionFactory);
        notebook = new Notebook();
        notebook.setProducer("Producer");
        notebook.setModel("Model");
        notebook.setCategory("notebook");
        notebook.setPrice(1000);
        notebook.setDescription("Description");
        notebook.setImageRef("Producer_Model_1.jpg");
        notebook.setDisplayDiagonal("5.5");
        notebook.setProcessor("processor");
        notebook.setRam(2);
        notebook.setHdd(500);

        tableCleaner.cleanTables("notebook", "storage");

    }

    @Test
    public void testCreateNotebook() {

        notebookDAO.createNotebook(notebook);

        List<Notebook> notebooks = notebookDAO.getAllNotebooks();
        assertEquals(1, notebooks.size());
        StorageDAO storageDAO = new StorageDAO(connectionFactory);
        List<StoragePosition> storagePositions = storageDAO.getAllStoragePositions();
        assertEquals(1, storagePositions.size());
    }

    @Test
    public void testCreateNotebookRollback() {

        Notebook notebook = new Notebook();

        notebookDAO.createNotebook(notebook);

        List<Notebook> notebooks = notebookDAO.getAllNotebooks();
        assertEquals(0, notebooks.size());
        StorageDAO storageDAO = new StorageDAO(connectionFactory);
        List<StoragePosition> storagePositions = storageDAO.getAllStoragePositions();
        assertEquals(0, storagePositions.size());
    }

    @Test
    public void testGetNotebookById() {

        notebookDAO.createNotebook(notebook);

        List<Notebook> notebooks = notebookDAO.getAllNotebooks();
        int id = notebooks.get(0).getId();

        Notebook notebookFromDatabase = notebookDAO.getNotebookById(id);

        assertEquals(notebook, notebookFromDatabase);


    }

    @Test
    public void testGetId() {

        notebookDAO.createNotebook(notebook);

        int id = -1;
        id = notebookDAO.getId("Producer", "Model");

        assertTrue(id > 0);
    }

    @Test
    public void testUpdateNotebook() {

        notebookDAO.createNotebook(notebook);

        List<Notebook> notebooks = notebookDAO.getAllNotebooks();
        int id = notebooks.get(0).getId();
        notebook.setId(id);
        notebook.setHdd(1000);

        notebookDAO.updateNotebook(notebook);
        notebooks = notebookDAO.getAllNotebooks();

        assertEquals(1, notebooks.size());
        Notebook notebookFromDB = notebooks.get(0);
        assertEquals(1000, notebookFromDB.getHdd());
    }

    @Test
    public void testDeleteNotebookByArticle() {

        notebookDAO.createNotebook(notebook);

        List<Notebook> notebooks = notebookDAO.getAllNotebooks();
        assertEquals(1, notebooks.size());
        int article = notebooks.get(0).getArticle();

        notebookDAO.deleteNotebookByArticle(article);
        notebooks = notebookDAO.getAllNotebooks();
        assertEquals(0, notebooks.size());
    }
}