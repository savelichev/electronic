package ua.savelichev.electronic.domain.services.product;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.PhoneDAO;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IPhoneDAO;
import ua.savelichev.electronic.dao.interfaces.IStorageDAO;
import ua.savelichev.electronic.domain.entity.Phone;
import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.services.StorageService;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PhoneServiceTest {

    IDAOFactory mockDaoFactory;
    IPhoneDAO mockPhoneDAO;
    PhoneService phoneService;

    @Before
    public void init() {
        mockPhoneDAO = mock(PhoneDAO.class);
        mockDaoFactory = mock(IDAOFactory.class);
        when(mockDaoFactory.getPhoneDAO()).thenReturn(mockPhoneDAO);
        phoneService = new PhoneService(mockDaoFactory);
    }

    @Test
    public void testGetId() {
        Product mockProduct = mock(Product.class);
        when(mockPhoneDAO.getId(anyString(), anyString())).thenReturn(123);
        assertEquals(123, phoneService.getId(mockProduct));
    }

    @Test
    public void testGetIdNulProduct() {
        assertEquals(0, phoneService.getId(null));
    }

    @Test
    public void testGetProductByArticle() {
        int article = 12345;
        Phone phone = new Phone();
        phoneService.getPhoneByArticle(article);
        when(mockPhoneDAO.getPhoneById(anyInt())).thenReturn(phone);
        assertEquals(phone, phoneService.getPhoneByArticle(article));
    }

    @Test
    public void testGetProductByNotValidArticle() {
        int article = 3;
        Phone phone = new Phone();
        phoneService.getPhoneByArticle(article);
        when(mockPhoneDAO.getPhoneById(anyInt())).thenReturn(phone);
        assertEquals(phone, phoneService.getPhoneByArticle(article));
    }

    @Test
    public void testGetAllPhones() {
        when(mockPhoneDAO.getAllPhones()).thenReturn(new ArrayList<Phone>());
        phoneService.getAllPhones();
    }

    @Test
    public void testGetPhoneById() {
        int id = 1;
        Phone phone = new Phone();
        when(mockPhoneDAO.getPhoneById(anyInt())).thenReturn(phone);
        assertEquals(phone, phoneService.getPhoneById(id));
    }

    @Test
    public void testAddPhone() {
        Phone phone = new Phone();
        phone.setId(1);
        phone.setProducer("Producer");
        phone.setModel("Model");
        phoneService.addPhone(phone);
    }

    @Test
    public void testDeletePhone() {
        int article = 12345;
        phoneService.deletePhoneByArticle(article);
    }


}