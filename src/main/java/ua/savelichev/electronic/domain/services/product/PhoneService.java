package ua.savelichev.electronic.domain.services.product;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.PhoneDAO;
import ua.savelichev.electronic.dao.interfaces.IPhoneDAO;
import ua.savelichev.electronic.domain.entity.Phone;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.List;

public class PhoneService implements ProductService {

    private static final Logger log = Logger.getLogger(PhoneService.class);

    /**
     * Gets id of Phone from database
     *
     * @param product target Product
     * @return int id
     */
    @Override
    public int getId(Product product) {

        IPhoneDAO phoneDAO = new PhoneDAO();
        int id = phoneDAO.getId(product.getProducer(), product.getModel());
        log.debug("Got id: " + id + " for product with producer: " + product.getProducer()
                + ", model: " + product.getModel());
        return id;
    }

    /**
     * Gets Phone from database by article
     *
     * @param article target product article
     * @return Phone object
     */
    @Override
    public Product getProductByArticle(int article) {
        int id = ProductUtils.getProductIdFromArticle(article);
        Phone phone = getPhoneById(id);
        log.debug("Got product by article: " + article);
        return phone;
    }

    /**
     * Gets all Phone objects from database
     *
     * @return List of Phone objects
     */
    public List<Phone> getAllPhones() {
        IPhoneDAO phoneDAO = new PhoneDAO();
        List<Phone> phones = phoneDAO.getAllPhones();
        log.debug("Got list of all phones");
        return phones;
    }

    /**
     * Gets Phone from database by id
     *
     * @param id target Phone id
     * @return Phone object
     */
    public Phone getPhoneById(int id) {
        IPhoneDAO phoneDAO = new PhoneDAO();
        Phone phone = phoneDAO.getPhoneById(id);
        log.debug("Got phone by id: " + id);
        return phone;
    }

    /**
     * Gets Phone from database by article
     *
     * @param article target Phone article
     * @return Phone object
     */
    public Phone getPhoneByArticle(int article) {
        int id = ProductUtils.getProductIdFromArticle(article);
        Phone phone = getPhoneById(id);
        log.debug("Got phone by article: " + article);
        return phone;
    }

    /**
     * Inserts Phone into database
     * <p>
     * First: inserts phone without article
     * Second: selects id of current row
     * Third: generates article for current phone
     * Fourth: inserts generated article into database
     *
     * @param phone contains new Phone parameters
     */
    public void addPhone(Phone phone) {
        IPhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.createPhone(phone);
        int id = phoneDAO.getId(phone.getProducer(), phone.getModel());
        int article = ProductUtils.generateProductArticle(phone.getCategory(), id);
        phone.setArticle(article);
        phoneDAO.updatePhone(phone);
        log.info("Added new Phone: " + phone);
    }

    /**
     * Deletes Phone from database
     *
     * @param phoneArticle target Phone article
     */
    public void deletePhoneByArticle(String phoneArticle) {
        IPhoneDAO phoneDAO = new PhoneDAO();
        int article = Integer.valueOf(phoneArticle);
        phoneDAO.deletePhoneByArticle(article);
        log.info("Deleted phone by article: " + article);
    }
}
