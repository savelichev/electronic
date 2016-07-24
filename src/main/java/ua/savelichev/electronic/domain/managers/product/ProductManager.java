package ua.savelichev.electronic.domain.managers.product;

import ua.savelichev.electronic.domain.entity.Product;

public interface ProductManager {

    int getId(Product product);

    Product getProductByArticle(int article);
}
