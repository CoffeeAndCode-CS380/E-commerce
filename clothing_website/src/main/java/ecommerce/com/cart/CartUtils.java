package ecommerce.com.cart;

import ecommerce.com.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.*;
import java.util.*;

/**  a CartUtil that handles User’s shopping cart  interactions  and calculate total*/
public class CartUtils {

    private static final CartUtils INSTANCE = new CartUtils();
    public static CartUtils get() { return INSTANCE; }
    private final ObservableList<Product> items = FXCollections.observableArrayList(); // holds cart items

    /**
     * Add a product to the cart
     * @param product product
     *
     */
    public void addItem(Product product) {
        items.add(product);
    }

    /**
     * Remove a product from the cart
     * @param product  product to be removed
     * @return true if it was present & removed, false otherwise
     */
    public boolean removeItem(Product product) {
        return items.remove(product);
    }


    /** Compute the total price of all items
     * @return total cost
     * */
    public double getTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    /**
     * @return number of items in the cart */
    public int getItemCount() {
        return items.size();
    }

    /**return the observable list of products in the cart
     * i.e. view of the cart’s items
     *  */
    public ObservableList<Product> getItems() {
        return items;
    }

}