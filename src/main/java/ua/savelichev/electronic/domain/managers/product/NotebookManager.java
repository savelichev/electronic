package ua.savelichev.electronic.domain.managers.product;


import ua.savelichev.electronic.dao.NotebookDAOImpl;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.List;

public class NotebookManager implements ProductManager {



    public List<Notebook> getAllNotebooks() {

        return new NotebookDAOImpl().getAllNotebooks();
    }

    public Notebook getNotebookById(int id) {

        return new NotebookDAOImpl().getNotebookById(id);
    }

    public Notebook getNotebookByArticle(int article) {

        return getNotebookById(ProductUtils.getProductIdFromArticle(article));
    }



    @Override
    public int getId(Product product) {
        return new NotebookDAOImpl().getId((Notebook) product);
    }

    @Override
    public Product getProductByArticle(int article) {
       return getNotebookById(Integer.valueOf(String.valueOf(article).substring(2, String.valueOf(article).length())));
    }

    public void addNotebook(Notebook notebook) {

        NotebookDAOImpl notebookDAO = new NotebookDAOImpl();

        notebookDAO.createNotebook(notebook);

        notebook.setId(notebookDAO.getId(notebook));

        notebook.setArticle(ProductUtils.generateProductArticle(notebook.getCategory(),notebook.getId()));

        notebookDAO.updateNotebook(notebook);


    }


    public void deleteNotebook(String notebookArticle) {

        new NotebookDAOImpl().deleteNotebookByArticle(Integer.valueOf(notebookArticle));
    }
}
