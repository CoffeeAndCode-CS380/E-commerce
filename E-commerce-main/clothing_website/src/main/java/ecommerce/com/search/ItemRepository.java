package ecommerce.com.search;


import ecommerce.com.product.Product;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class ItemRepository {
    // 1) Our “in-memory catalog”
    private static final List<Product> products = new ArrayList<>();

    static {
        // TODO: replace with real data or load from DB
        products.add(new Product("T-Shirt", "Clothing", 19.99,
                "/ecommerce/com/site icons/light_blue_jeans.jpg"));
        products.add(new Product("Jeans", "Clothing", 49.99,
                "/images/grey_hoodie.png"));

        products.add(new Product(
                "Black Jeans", 90.00, "32", "Cloth", "BJ01",
                "/ecommerce/com/site icons/black_jeans.jpg"
        ));
    }

    /**
     * Returns an unmodifiable list of every product.
     */
    public static List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

}
