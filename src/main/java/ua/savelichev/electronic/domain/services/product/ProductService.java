package ua.savelichev.electronic.domain.services.product;

import ua.savelichev.electronic.domain.entity.Product;

public interface ProductService {

    int getId(Product product);

    Product getProductByArticle(int article);
}
