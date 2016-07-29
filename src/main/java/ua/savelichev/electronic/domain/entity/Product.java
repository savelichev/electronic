package ua.savelichev.electronic.domain.entity;

/**
 * Basic class for all products
 * Contains common fields of product.
 */
public abstract class Product {

    /**
     * Product identifier
     */
    private int id;


    /**
     * Category of product, has the same name as table relevant this category.
     * Example: category "notebook", table name "notebook".
     */
    private String category;

    /**
     * Producer(brand) of product
     */
    private String producer;

    /**
     * Model of product.
     */
    private String model;

    /**
     * Foreign key on table "storage" where contains information about pruduct amount
     */
    private int storageId;

    /**
     * Product price.
     */
    private int price;

    /**
     * Product description.
     */
    private String description;

    /**
     * Unique article of product.
     */
    private int article;

    /**
     * Image reference of product.
     */
    private String imageRef;


    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    /**
     * Compare Product producer and model for equals.
     *
     * @param o compare target.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!producer.equals(product.producer)) return false;
        return model.equals(product.model);

    }

    /**
     * Calculates hashCode of product by Product unique fields product and model.
     *
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        int result = producer.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", article=" + article +
                ", imageRef='" + imageRef + '\'' +
                '}';
    }
}
