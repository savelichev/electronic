package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.Notebook;

import java.util.List;

public interface INotebookDAO {

    void createNotebook(Notebook notebook);

    Notebook getNotebookById(int id);

    int getId(String producer, String model);

    List<Notebook> getAllNotebooks();

    void updateNotebook(Notebook notebook);

    void deleteNotebookByArticle(int article);
}
