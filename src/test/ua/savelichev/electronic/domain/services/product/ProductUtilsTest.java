package ua.savelichev.electronic.domain.services.product;

import org.junit.Test;

import static org.junit.Assert.*;


public class ProductUtilsTest {


    @Test
    public void testGenerateProductArticle() {
        String category = "notebook";
        int id = 1234;
        assertEquals(131234, ProductUtils.generateProductArticle(category, id));
    }

    @Test
    public void testGenerateProductArticleForNullCategory() {
        int id = 1234;
        assertEquals(0, ProductUtils.generateProductArticle(null, id));
    }

    @Test
    public void testGenerateProductArticleForZeroId() {
        String category = "notebook";
        assertEquals(0, ProductUtils.generateProductArticle(category, 0));
    }

    @Test
    public void testGenerateProductArticleForNotValidCategory() {
        int id = 1234;
        String category = "test";
        assertEquals(0, ProductUtils.generateProductArticle(category, id));
    }

    @Test
    public void testGetProductIdFromArticle() {
        int id = 1234;
        int article = 131234;
        assertEquals(id, ProductUtils.getProductIdFromArticle(article));
    }

    @Test
    public void testGetProductIdFromNotValidArticle() {
        int article = 3;
        assertEquals(0, ProductUtils.getProductIdFromArticle(article));
    }

    @Test
    public void testGetProductCategoryCodeFromArticle() {
        int article = 131234;
        assertEquals(13, ProductUtils.getProductCategoryCodeFromArticle(article));
    }

    @Test
    public void testGetProductCategoryCodeFromNotValidArticle() {
        int article = 3;
        assertEquals(0, ProductUtils.getProductCategoryCodeFromArticle(article));
    }

    @Test
    public void testGetProductCategoryFromArticle() {
        int article = 131234;
        assertEquals("notebook", ProductUtils.getProductCategoryFromArticle(article));
    }

    @Test
    public void testGetProductCategoryFromNotValidArticle() {
        int article = 3;
        assertNull(ProductUtils.getProductCategoryFromArticle(article));
    }

    @Test
    public void testGetCodeOfCategory() {
        int expectedCategoryCode = 13;
        String category = "notebook";
        assertEquals(expectedCategoryCode, ProductUtils.getCodeOfCategory(category));
    }

    @Test
    public void testGetCodeOfNotValidCategory() {
        int expectedCategoryCode = 0;
        String category = "test";
        assertEquals(expectedCategoryCode, ProductUtils.getCodeOfCategory(category));
    }


    @Test
    public void testGetProductServiceByCategoryNotebook() {
        String category = "notebook";
        assertTrue(ProductUtils.getProductServiceByCategory(category) instanceof NotebookService);
    }

    @Test
    public void testGetProductServiceByCategoryPhone() {
        String category = "phone";
        assertTrue(ProductUtils.getProductServiceByCategory(category) instanceof PhoneService);
    }

    @Test
    public void testGetProductServiceByNotValidCategory() {
        String category = "test";
        assertNull(ProductUtils.getProductServiceByCategory(category));
    }

    @Test
    public void testGetProductServiceByCategoryForNull() {
        assertNull(ProductUtils.getProductServiceByCategory(null));
    }



}