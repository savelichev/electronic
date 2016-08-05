package ua.savelichev.electronic.domain.services.product;

import ua.savelichev.electronic.domain.entity.Product;

public interface IProductService {

    /**
     * Gets product id from relevant table in database
     *
     * @param product target Product
     * @return int id of Product
     */
    int getId(Product product);

    /**
     * Gets Product from database by article
     *
     * @param article target product article
     * @return Product object
     */
    Product getProductByArticle(int article);
}
