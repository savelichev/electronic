package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.Notebook;

import java.util.List;

public interface INotebookDAO {

    void createNotebook(Notebook notebook);

    Notebook getNotebookById(int id);

    int getId(Notebook notebook);

    List<Notebook> getAllNotebooks();

    void updateNotebook(Notebook notebook);

    void deleteNotebookByArticle(int article);


    List<Notebook> getNotebooksByPrice(int minPrice, int maxPrice);

    List<Notebook> getNotebooksByDiagonal(int minDiagonal, int maxDiagonal);


}
