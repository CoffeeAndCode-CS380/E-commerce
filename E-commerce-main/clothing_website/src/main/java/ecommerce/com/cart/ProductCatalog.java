package ecommerce.com.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import  java.util.*;

/**
 * mange invertry of prod
 */
public class ProductCatalog {
    private List<Product> products = new ArrayList<>();

    public  List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }
    /**
     * Finds a product by its ID.
     * @param id unique identifier
     * @return Optional containing product if found
     */
    public Optional<Product> findById(String id) { // method to find by id
        for (Product p : products) {               // iterate through products
            if (p.getId().equals(id)) {            // check for id match
                return Optional.of(p);             // return found product
            }
        }
        return Optional.empty();                   // no match found
    }

    /**
     * Searches products by a name fragment.
     * @param nameFragment substring to search
     * @return list of matching products
     */
    public List<Product> searchByName(String nameFragment) { // method to search by name
        List<Product> result = new ArrayList<>();            // list for results
        for (Product p : products) {                         // iterate through products
            if (p.getName().toLowerCase().contains(nameFragment.toLowerCase())) { // case-insensitive match
                result.add(p);                              // add matching product
            }
        }
        return result;                                      // return search results
    }

    /**
     * Adds a product to the catalog.
     * @param product product to add
     */
    public void addProduct(Product product) { // method to add product
        products.add(product);               // add product to list
    }
}