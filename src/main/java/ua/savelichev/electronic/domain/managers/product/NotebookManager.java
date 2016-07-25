package ua.savelichev.electronic.domain.managers.product;


import ua.savelichev.electronic.dao.NotebookDAO;
import ua.savelichev.electronic.domain.entity.Notebook;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.List;

public class NotebookManager implements ProductManager {



    public List<Notebook> getAllNotebooks() {

        return new NotebookDAO().getAllNotebooks();
    }

    public Notebook getNotebookById(int id) {

        return new NotebookDAO().getNotebookById(id);
    }

    public Notebook getNotebookByArticle(int article) {

        return getNotebookById(ProductUtils.getProductIdFromArticle(article));
    }



    @Override
    public int getId(Product product) {
        return new NotebookDAO().getId((Notebook) product);
    }

    @Override
    public Product getProductByArticle(int article) {
       return getNotebookById(Integer.valueOf(String.valueOf(article).substring(2, String.valueOf(article).length())));
    }

    public void addNotebook(Notebook notebook) {

        NotebookDAO notebookDAO = new NotebookDAO();

        notebookDAO.createNotebook(notebook);

        notebook.setId(notebookDAO.getId(notebook));

        notebook.setArticle(ProductUtils.generateProductArticle(notebook.getCategory(),notebook.getId()));

        notebookDAO.updateNotebook(notebook);


    }


    public void deleteNotebook(String notebookArticle) {

        new NotebookDAO().deleteNotebookByArticle(Integer.valueOf(notebookArticle));
    }
}
