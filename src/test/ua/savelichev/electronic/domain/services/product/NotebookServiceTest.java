package ua.savelichev.electronic.domain.services.product;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.NotebookDAO;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.INotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Savelich on 03.08.2016.
 */
public class NotebookServiceTest {


    IDAOFactory mockDaoFactory;
    INotebookDAO mockNotebookDAO;
    NotebookService notebookService;

    @Before
    public void init() {
        mockNotebookDAO = mock(NotebookDAO.class);
        mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getNotebookDAO()).thenReturn(mockNotebookDAO);
        notebookService = new NotebookService(mockDaoFactory);
    }

    @Test
    public void testGetId() {
        Product mockProduct = mock(Product.class);
        when(mockNotebookDAO.getId(anyString(), anyString())).thenReturn(123);
        assertEquals(123, notebookService.getId(mockProduct));
    }

    @Test
    public void testGetIdNulProduct() {
        assertEquals(0, notebookService.getId(null));
    }

    @Test
    public void testGetProductByArticle() {
        int article = 12345;
        Notebook notebook = new Notebook();
        notebookService.getNotebookByArticle(article);
        when(mockNotebookDAO.getNotebookById(anyInt())).thenReturn(notebook);
        assertEquals(notebook, notebookService.getNotebookByArticle(article));
    }

    @Test
    public void testGetProductByNotValidArticle() {
        int article = 3;
        Notebook notebook = new Notebook();
        notebookService.getNotebookByArticle(article);
        when(mockNotebookDAO.getNotebookById(anyInt())).thenReturn(notebook);
        assertEquals(notebook, notebookService.getNotebookByArticle(article));
    }

    @Test
    public void testGetAllNotebooks() {
        when(mockNotebookDAO.getAllNotebooks()).thenReturn(new ArrayList<Notebook>());
        notebookService.getAllNotebooks();
    }

    @Test
    public void testGetNotebookById() {
        int id = 1;
        Notebook notebook = new Notebook();
        when(mockNotebookDAO.getNotebookById(anyInt())).thenReturn(notebook);
        assertEquals(notebook, notebookService.getNotebookById(id));
    }

    @Test
    public void testAddNotebook() {
        Notebook notebook = new Notebook();
        notebook.setId(1);
        notebook.setProducer("Producer");
        notebook.setModel("Model");
        notebookService.addNotebook(notebook);
    }

    @Test
    public void testDeleteNotebook() {
        int article = 12345;
        notebookService.deleteNotebookByArticle(article);
    }

}