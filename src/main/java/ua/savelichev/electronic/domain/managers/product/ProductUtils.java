package ua.savelichev.electronic.domain.managers.product;


import ua.savelichev.electronic.domain.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {

    private ProductUtils() {
    }

    public static final int TABLE_CODE_ARTICLE_PART_BEGIN = 0;
    public static final int TABLE_CODE_ARTICLE_PART_END = 2;

    public static final int ID_ARTICLE_PART_BEGIN = 2;

    private static final Map<Integer, String> PRODUCT_CATEGORIES_CODE = new HashMap<Integer, String>() {{
        put(10, "audio");
        put(11, "camera");
        put(12, "tv");
        put(13, "notebook");
        put(14, "phone");
        put(15, "accessory");
    }};


    public static int getProductIdFromArticle(int article) {
        return Integer.valueOf(String.valueOf(article).substring(ID_ARTICLE_PART_BEGIN, String.valueOf(article).length()));
    }

    public static int getProductCategoryCodeFromArticle(int article) {
        return Integer.valueOf(String.valueOf(article).substring(TABLE_CODE_ARTICLE_PART_BEGIN, TABLE_CODE_ARTICLE_PART_END));
    }

    public static String getProductCategoryFromArticle(int article) {
        return PRODUCT_CATEGORIES_CODE.get(getProductCategoryCodeFromArticle(article));
    }

    public static int getCodeOfCategory(String category){

        int productCategoryCode = 0;

        for (int key : PRODUCT_CATEGORIES_CODE.keySet()) {
            String tempCategory = PRODUCT_CATEGORIES_CODE.get(key);
            if (tempCategory.equals(category)) {
                productCategoryCode = key;
            }
        }
        return productCategoryCode;
    }

    public static ProductManager createManagerByCategory(String category) {
        switch (category) {
            case "notebook":
                return new NotebookManager();
            case "phone":
                return new PhoneManager();

        }
        return null;
    }

    public static int generateProductArticle(String category, int id) {

        return Integer.valueOf(""+ getCodeOfCategory(category)+id);
    }

    public static Product getProductByArticle(int article) {
        return createManagerByCategory(getProductCategoryFromArticle(article)).getProductByArticle(article);
    }
}
