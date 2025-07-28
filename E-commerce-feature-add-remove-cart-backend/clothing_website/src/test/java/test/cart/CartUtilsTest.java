
package ecommerce.com.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CartUtils.
 */

import ecommerce.com.cart.CartUtils;
import ecommerce.com.cart.Product;

public class CartUtilsTest {

    private CartUtils cart;               // cart instance under test
    private Product apple;                // sample product 1
    private Product banana;               // sample product 2

    @BeforeEach
    void setUp() {
        cart = new CartUtils();           // create fresh cart before each test
        apple  = new Product("1", "Apple",  0.99);  // product with price 0.99
        banana = new Product("2", "Banana", 1.49);  // product with price 1.49
    }

    @Test
    void testAddItemIncreasesCount() {
        assertEquals(0, cart.getItemCount(),
                "Cart should start empty");
        cart.addItem(apple);              // add one item
        assertEquals(1, cart.getItemCount(),
                "Adding one item should increment count");
    }

    @Test
    void testRemoveItemDecreasesCount() {
        cart.addItem(apple);
        cart.addItem(banana);
        assertTrue(cart.removeItem(apple),
                "Should return true when item was present");
        assertEquals(1, cart.getItemCount(),
                "Removing one should decrement count");
    }

    @Test
    void testRemoveNonexistentReturnsFalse() {
        assertFalse(cart.removeItem(apple),
                "Removing an item not in cart should return false");
    }

    @Test
    void testGetTotalSumsPrices() {
        cart.addItem(apple);
        cart.addItem(banana);
        double expected = 0.99 + 1.49;
        assertEquals(expected, cart.getTotal(), 1e-6,
                "Total should equal sum of item prices");
    }

    @Test
    void testEmptyCartTotalIsZero() {
        assertEquals(0.0, cart.getTotal(), 1e-6,
                "Empty cart total must be 0");
    }
}
