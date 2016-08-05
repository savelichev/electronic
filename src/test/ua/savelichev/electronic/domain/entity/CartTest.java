package ua.savelichev.electronic.domain.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartTest {

    Cart cart;
    CartItem cartItem;

    @Before
    public void init() {
        cart = new Cart();

        Product mockProduct = mock(Product.class);
        when(mockProduct.getPrice()).thenReturn(100);
        cartItem = new CartItem(mockProduct, 1);

    }

    @Test
    public void testAddCartItem() throws Exception {
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartItems().size());
    }

    @Test
    public void testAddCartItemIncreaseAmount() throws Exception {
        cart.addCartItem(cartItem);
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(2, cart.getCartItems().get(0).getAmount());
    }

    @Test
    public void testDecreaseCartItemAmount() throws Exception {
        cart.addCartItem(cartItem);
        cart.addCartItem(cartItem);
        assertEquals(2, cart.getCartItems().get(0).getAmount());

        cart.decreaseCartItemAmount(cartItem);
        assertEquals(1, cart.getCartItems().get(0).getAmount());

        cart.decreaseCartItemAmount(cartItem);
        assertEquals(1, cart.getCartItems().get(0).getAmount());
    }

    @Test
    public void testRemoveCartItem() throws Exception {
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartItems().size());

        cart.removeCartItem(cartItem);
        assertEquals(0, cart.getCartItems().size());
    }

    @Test
    public void testCalculateCartCost() throws Exception {
        cart.addCartItem(cartItem);
        assertEquals(100, cart.getCartCost());

        cart.addCartItem(cartItem);
        assertEquals(200, cart.getCartCost());

        cart.decreaseCartItemAmount(cartItem);
        assertEquals(100, cart.getCartCost());

        cart.removeCartItem(cartItem);
        assertEquals(0, cart.getCartCost());

    }


}