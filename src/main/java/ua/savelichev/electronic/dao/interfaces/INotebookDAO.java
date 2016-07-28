package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.Notebook;

import java.util.List;

public interface INotebookDAO {

    /**
     * Inserts row to the table "notebook"
     *
     * @param notebook target for inserting
     */
    void createNotebook(Notebook notebook);

    /**
     * Selects row from the table "notebook" and builds Notebook object
     *
     * @param id relevant row id
     * @return  Notebook object
     */
    Notebook getNotebookById(int id);

    /**
     * Selects id from the table "notebook" by fields "producer" and "model"
     *
     * @param producer field "producer" parameter
     * @param model    field "model" parameter
     * @return int "id" of relevant row
     */
    int getId(String producer, String model);

    /**
     * Selects all rows from table "notebook"
     *
     * @return List of Notebook objects
     */
    List<Notebook> getAllNotebooks();

    /**
     * Updates relevant row in table "notebook"
     *
     * @param notebook contains new parameters for relevant row
     */
    void updateNotebook(Notebook notebook);

    /**
     * Deletes row in table "notebook" by field "article"
     *
     * @param article target row article
     */
    void deleteNotebookByArticle(int article);
}
