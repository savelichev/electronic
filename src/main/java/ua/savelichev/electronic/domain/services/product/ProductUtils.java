package ua.savelichev.electronic.domain.services.product;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.dao.DAOFactory;
import ua.savelichev.electronic.domain.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {

    private static final Logger log = Logger.getLogger(ProductUtils.class);

    private ProductUtils() {
    }

    public static final int TABLE_CODE_ARTICLE_PART_BEGIN = 0;
    public static final int TABLE_CODE_ARTICLE_PART_END = 2;
    public static final int ID_ARTICLE_PART_BEGIN = 2;
    public static final int MINIMAL_ARTICLE_IN_APPLICATION = 101;
    private static final Map<Integer, String> PRODUCT_CATEGORIES_CODE = new HashMap<Integer, String>() {{
        put(10, "audio");
        put(11, "camera");
        put(12, "tv");
        put(13, "notebook");
        put(14, "phone");
        put(15, "accessory");
    }};

    /**
     * Generates product article which is a category code and id in product table are united
     * First two digits are the category code, other are the product id.
     * Example: 134  = "13"+"4". where "13" - category code(means "notebook")
     * and "4" is id(primary key) in "notebook" table
     *
     * @param category
     * @param id
     * @return article
     */
    public static int generateProductArticle(String category, int id) {

        int categoryCode = getCodeOfCategory(category);

        if (category == null || id < 1 || categoryCode < 10) {
            log.debug("Generate article fold: " + " for product with category: " + category + ", id: " + id);
            return 0;
        } else {
            int article = Integer.valueOf("" + categoryCode + id);
            log.debug("Generated article: " + article + " for product with category: " + category + ", id: " + id);
            return article;
        }
    }

    /**
     * Extracts from article product id(primary key in relevant table)
     *
     * @param article
     * @return id
     */
    public static int getProductIdFromArticle(int article) {

        if (article > MINIMAL_ARTICLE_IN_APPLICATION) {
            int id = Integer.valueOf(String.valueOf(article)
                    .substring(ID_ARTICLE_PART_BEGIN, String.valueOf(article).length()));
            log.debug("Got id: " + id + " from article: " + article);
            return id;
        } else return 0;
    }

    /**
     * Extracts from article product category code
     *
     * @param article
     * @return category code
     */
    public static int getProductCategoryCodeFromArticle(int article) {

        if (article > MINIMAL_ARTICLE_IN_APPLICATION) {
            int categoryCode = Integer.valueOf(String.valueOf(article)
                    .substring(TABLE_CODE_ARTICLE_PART_BEGIN, TABLE_CODE_ARTICLE_PART_END));
            log.debug("Got category code: " + categoryCode + " from article: " + article);
            return categoryCode;
        } else return 0;
    }

    /**
     * Extracts from article product category
     *
     * @param article
     * @return category
     */
    public static String getProductCategoryFromArticle(int article) {

        if (article > MINIMAL_ARTICLE_IN_APPLICATION) {
            String category = PRODUCT_CATEGORIES_CODE.get(getProductCategoryCodeFromArticle(article));
            log.debug("Got product category: " + category + " from article: " + article);
            return category;

        } else return null;

    }

    /**
     * Extracts category code from PRODUCT_CATEGORIES_CODE.
     * <p>
     * Uses when new article generates.
     *
     * @param category
     * @return category code
     */
    public static int getCodeOfCategory(String category) {

        int categoryCode = 0;

        for (int key : PRODUCT_CATEGORIES_CODE.keySet()) {
            String tempCategory = PRODUCT_CATEGORIES_CODE.get(key);
            if (tempCategory.equals(category)) {
                categoryCode = key;
            }
        }
        log.debug("Got category code: " + categoryCode + " of category: " + category);
        return categoryCode;
    }

    /**
     * factory method which creates new product service relevant input category
     *
     * @param category
     * @return IProductService
     */
    public static IProductService getProductServiceByCategory(String category) throws NullPointerException {
        if (category == null) {
            log.error("No IProductService for null statement");
            return null;
        }
        switch (category) {
            case "notebook":
                NotebookService notebookService = new NotebookService(DAOFactory.getInstance());
                log.debug("Got new NotebookService for category: " + category);
                return notebookService;
            case "phone":
                PhoneService phoneService = new PhoneService(DAOFactory.getInstance());
                log.debug("Got new PhoneService for category: " + category);
                return phoneService;

        }
        log.error("No IProductService for category: " + category);
        return null;
    }


    public static Product getProductByArticle(int article) throws NullPointerException {

        if (article > MINIMAL_ARTICLE_IN_APPLICATION) {
            String productCategory = getProductCategoryFromArticle(article);
            IProductService productService = getProductServiceByCategory(productCategory);
            Product product = null;
            if (productService != null) {
                product = productService.getProductByArticle(article);
            }
            return product;
        }
        return null;
    }
}
