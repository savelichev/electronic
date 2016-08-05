package ua.savelichev.electronic.domain.services;


import org.junit.Before;
import org.junit.Test;
import ua.savelichev.electronic.domain.entity.Cart;
import ua.savelichev.electronic.domain.entity.Product;
import ua.savelichev.electronic.domain.entity.interfaces.ICart;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CartServiceTest {

    ICart mockCart;
    Product mockProduct = mock(Product.class);
    ICartService cartService;

    @Before
    public void init() {
        mockCart = mock(ICart.class);
        cartService = new CartService();
    }

    @Test
    public void testAddProductReturnsTheSameCart() {

        ICart cart = cartService.addProduct(mockCart, mockProduct);
        assertSame(mockCart, cart);
    }

    @Test
    public void testAddProductReturnsDifferentCartsIfNull() {

        ICart nullCart = null;
        ICart cart = cartService.addProduct(nullCart, mockProduct);
        assertNotSame(nullCart, cart);
    }

    @Test
    public void testAddProductForNullProduct() {

        ICart nullCart = null;
        ICart cart = cartService.addProduct(nullCart, mockProduct);
        assertNotSame(nullCart, cart);
    }


    @Test
    public void testRemoveProduct() {
        cartService.removeProduct(mockCart, mockProduct);
    }

    @Test
    public void testRemoveProductForNullProduct() {
         cartService.removeProduct(mockCart, null);
    }

    @Test
    public void testRemoveProductForNullCart() {
        cartService.removeProduct(null, mockProduct);
    }

    @Test
    public void testDecreaseProductAmount() {
        cartService.removeProduct(mockCart, mockProduct);
    }

    @Test
    public void testDecreaseProductAmountForNullProduct() {
        cartService.decreaseProductAmount(mockCart, null);
    }

    @Test
    public void testDecreaseProductAmountForNullCart() {
        cartService.decreaseProductAmount(null, mockProduct);
    }




}
