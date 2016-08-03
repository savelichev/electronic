package ua.savelichev.electronic.domain.services.product;

import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.interfaces.IDAOFactory;
import ua.savelichev.electronic.dao.interfaces.IPhoneDAO;
import ua.savelichev.electronic.domain.entity.Phone;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.List;

public class PhoneService implements IProductService {

    private static final Logger log = Logger.getLogger(PhoneService.class);

    private IDAOFactory daoFactory;

    public PhoneService(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Gets id of Phone from database
     *
     * @param product target Product
     * @return int id
     */
    @Override
    public int getId(Product product) {
        if (product != null) {
            IPhoneDAO phoneDAO = daoFactory.getPhoneDAO();
            int id = phoneDAO.getId(product.getProducer(), product.getModel());
            log.debug("Got id: " + id + " for product with producer: " + product.getProducer()
                    + ", model: " + product.getModel());
            return id;
        } else return 0;
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
        IPhoneDAO phoneDAO = daoFactory.getPhoneDAO();
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
        IPhoneDAO phoneDAO = daoFactory.getPhoneDAO();
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
        if (phone == null) {
            return;
        }
        IPhoneDAO phoneDAO = daoFactory.getPhoneDAO();
        log.info("Sending query for insert new Phone: " + phone);
        phoneDAO.createPhone(phone);

    }

    /**
     * Deletes Phone from database
     *
     * @param article target Phone article
     */
    public void deletePhoneByArticle(int article) {
        IPhoneDAO phoneDAO = daoFactory.getPhoneDAO();

        phoneDAO.deletePhoneByArticle(article);
        log.info("Send request to DAO for deletion phone by article: " + article);
    }
}
