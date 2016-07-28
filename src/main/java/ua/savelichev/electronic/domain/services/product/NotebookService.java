package ua.savelichev.electronic.domain.services.product;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.NotebookDAO;
import ua.savelichev.electronic.dao.interfaces.INotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.List;

public class NotebookService implements ProductService {

    private static final Logger log = Logger.getLogger(NotebookService.class);

    private INotebookDAO notebookDAO;

    /**
     * Gets id of Notebook from database
     *
     * @param product target Product
     * @return int id
     */
    @Override
    public int getId(Product product) {

        notebookDAO = new NotebookDAO();
        int id = notebookDAO.getId(product.getProducer(), product.getModel());
        log.debug("Got id: " + id + " for product with producer: " + product.getProducer()
                + ", model: " + product.getModel());
        return id;
    }

    /**
     * Gets Notebook from database by article
     *
     * @param article target product article
     * @return Notebook object
     */
    @Override
    public Product getProductByArticle(int article) {
        int id = ProductUtils.getProductIdFromArticle(article);
        Notebook notebook = getNotebookById(id);
        log.debug("Got product by article: " + article);
        return notebook;
    }

    /**
     * Gets all Notebook objects from database
     *
     * @return List of Notebook objects
     */
    public List<Notebook> getAllNotebooks() {
        notebookDAO = new NotebookDAO();
        List<Notebook> notebooks = notebookDAO.getAllNotebooks();
        log.debug("Got list of all notebooks");
        return notebooks;
    }

    /**
     * Gets Notebook from database by id
     *
     * @param id target Notebook id
     * @return Notebook object
     */
    public Notebook getNotebookById(int id) {
        notebookDAO = new NotebookDAO();
        Notebook notebook = notebookDAO.getNotebookById(id);
        log.debug("Got notebook by id: " + id);
        return notebook;
    }

    /**
     * Gets Notebook from database by article
     *
     * @param article target Notebook article
     * @return Notebook object
     */
    public Notebook getNotebookByArticle(int article) {
        int id = ProductUtils.getProductIdFromArticle(article);
        Notebook notebook = getNotebookById(id);
        log.debug("Got notebook by article: " + article);
        return notebook;
    }

    /**
     * Inserts Notebook into database
     * <p>
     * First: inserts notebook without article
     * Second: selects id of current row
     * Third: generates article for current notebook
     * Fourth: inserts generated article into database
     *
     * @param notebook contains new Notebook parameters
     */
    public void addNotebook(Notebook notebook) {

        notebookDAO = new NotebookDAO();

        notebookDAO.createNotebook(notebook);

        int id = notebookDAO.getId(notebook.getProducer(), notebook.getModel());

        int article = ProductUtils.generateProductArticle(notebook.getCategory(), id);

        notebook.setArticle(article);

        notebookDAO.updateNotebook(notebook);

        log.info("Added new Notebook: " + notebook);
    }

    /**
     * Deletes Notebook from database
     *
     * @param notebookArticle target Notebook article
     */
    public void deleteNotebookByArticle(String notebookArticle) {
        int article = Integer.valueOf(notebookArticle);
        notebookDAO.deleteNotebookByArticle(article);
        log.info("Deleted notebook by article: " + article);
    }
}
