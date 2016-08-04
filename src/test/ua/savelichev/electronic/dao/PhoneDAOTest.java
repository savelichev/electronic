package ua.savelichev.electronic.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;
import ua.savelichev.electronic.dao.interfaces.IPhoneDAO;
import ua.savelichev.electronic.domain.entity.Phone;
import ua.savelichev.electronic.domain.entity.StoragePosition;

import java.util.List;

import static org.junit.Assert.*;


public class PhoneDAOTest {

    IConnectionFactory connectionFactory;
    IPhoneDAO phoneDAO;
    TableCleaner tableCleaner;
    Phone phone;

    @Before
    public void init() {
        connectionFactory = ConnectionFactoryForTest.getInstance();
        phoneDAO = new PhoneDAO(connectionFactory);
        tableCleaner = new TableCleaner();
        phone = new Phone();
        phone.setProducer("Producer");
        phone.setModel("Model");
        phone.setCategory("phone");
        phone.setPrice(1000);
        phone.setDescription("Description");
        phone.setImageRef("Producer_Model_1.jpg");
        phone.setDisplayDiagonal("5.5");
        phone.setOperationSystem("OS");
        phone.setMainCamera("12");
        phone.setBatteryCapacity("2000");

        tableCleaner.cleanTables("phone", "storage");
    }

    @Test
    public void testCreatePhone() {

        phoneDAO.createPhone(phone);

        List<Phone> phones = phoneDAO.getAllPhones();
        assertEquals(1, phones.size());
        StorageDAO storageDAO = new StorageDAO(connectionFactory);
        List<StoragePosition> storagePositions = storageDAO.getAllStoragePositions();
        assertEquals(1, storagePositions.size());
    }

    @Test
    public void testCreatePhoneRollback() {

        Phone phone = new Phone();

        phoneDAO.createPhone(phone);

        List<Phone> phones = phoneDAO.getAllPhones();
        assertEquals(0, phones.size());
        StorageDAO storageDAO = new StorageDAO(connectionFactory);
        List<StoragePosition> storagePositions = storageDAO.getAllStoragePositions();
        assertEquals(0, storagePositions.size());
    }

    @Test
    public void testGetPhoneById() {

        phoneDAO.createPhone(phone);

        List<Phone> phones = phoneDAO.getAllPhones();
        int id = phones.get(0).getId();

        Phone phoneFromDatabase = phoneDAO.getPhoneById(id);

        assertEquals(phone, phoneFromDatabase);


    }

    @Test
    public void testGetId() {

        phoneDAO.createPhone(phone);


        int id = -1;
        id = phoneDAO.getId("Producer", "Model");

        assertTrue(id > 0);
    }

    @Test
    public void testUpdatePhone() {

        phoneDAO.createPhone(phone);

        List<Phone> phones = phoneDAO.getAllPhones();
        int id = phones.get(0).getId();
        phone.setId(id);
        phone.setMainCamera("23");

        phoneDAO.updatePhone(phone);
        phones = phoneDAO.getAllPhones();

        assertEquals(1, phones.size());
        Phone phoneFromDB = phones.get(0);
        assertEquals("23", phoneFromDB.getMainCamera());
    }

    @Test
    public void testDeletePhoneByArticle() {

        phoneDAO.createPhone(phone);

        List<Phone> phones = phoneDAO.getAllPhones();
        assertEquals(1, phones.size());
        int article = phones.get(0).getArticle();

        phoneDAO.deletePhoneByArticle(article);
        phones = phoneDAO.getAllPhones();
        assertEquals(0, phones.size());
    }
}