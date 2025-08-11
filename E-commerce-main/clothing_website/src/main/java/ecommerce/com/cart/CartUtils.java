package ecommerce.com.cart;

import ecommerce.com.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.*;


import java.util.*;

/** User’s shopping cart */
public class CartUtils {
    private static final CartUtils INSTANCE = new CartUtils();
    public static CartUtils get() { return INSTANCE; }
    private final ObservableList<Product> items = FXCollections.observableArrayList(); // holds cart items

    /** Add a product to the cart */
    public void addItem(Product product) {
        items.add(product);
    }

    /** Remove a product from the cart
     * @return true if it was present & removed, false otherwise */
    public boolean removeItem(Product product) {
        return items.remove(product);
    }

    /** Compute the total price of all items */
    public double getTotal() {
     return items.stream().mapToDouble(Product::getPrice).sum();
    }

    /** @return number of items in the cart */
    public int getItemCount() {
        return items.size();
    }

    /** @return unmodifiable view of the cart’s items */
    public ObservableList<Product> getItems() {
        return items;
    }

}