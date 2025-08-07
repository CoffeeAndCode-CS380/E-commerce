package ecommerce.com.cart;

import java.util.*;

/** User’s shopping cart */
public class CartUtils extends ecommerce.com.product.Product {

    private int quantity;

    private final List<Product> items = new ArrayList<>();  // holds cart items

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
        double total = 0;
        for (Product p : items) {
            total += p.getPrice() * quantity;
        }
        return total;
    }

    /** @return number of items in the cart */
    public int getItemCount() {
        return items.size();
    }

    /** @return unmodifiable view of the cart’s items */
    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }
}