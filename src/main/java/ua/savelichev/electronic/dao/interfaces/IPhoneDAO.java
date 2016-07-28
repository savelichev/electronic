package ua.savelichev.electronic.dao.interfaces;

import ua.savelichev.electronic.domain.entity.Phone;

import java.util.List;

public interface IPhoneDAO {

    /**
     * Inserts row to the table "phone"
     *
     * @param phone target for inserting
     */
    void createPhone(Phone phone);

    /**
     * Selects row from the table "phone" and builds Phone object
     *
     * @param id relevant row id
     * @return  Phone object
     */
    Phone getPhoneById(int id);

    /**
     * Selects id from the table "phone" by fields "producer" and "model"
     *
     * @param producer field "producer" parameter
     * @param model    field "model" parameter
     * @return int "id" of relevant row
     */
    int getId(String producer, String model);

    /**
     * Selects all rows from table "phone"
     *
     * @return List of Phone objects
     */
    List<Phone> getAllPhones();

    /**
     * Updates relevant row in table "phone"
     *
     * @param phone contains new parameters for relevant row
     */
    void updatePhone(Phone phone);

    /**
     * Deletes row in table "phone" by field "article"
     *
     * @param article target row article
     */
    void deletePhoneByArticle(int article);
}
