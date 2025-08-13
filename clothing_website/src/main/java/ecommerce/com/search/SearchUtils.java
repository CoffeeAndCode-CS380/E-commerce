package ecommerce.com.search;

import ecommerce.com.product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * this class handle the logic for the 3 types of search
 * searchByName, searchBySize, searchByCategory
 */
public class SearchUtils {

    /**
     * checks if the there is any with that search key inside the inventory, aka the list of our items
     * @param inventory
     * @param searchKey
     * @return true if that search key exists.
     */
    private static boolean containCharacter(String inventory, String searchKey){
        if(inventory == null || searchKey == null){
            return false;
        }
        return inventory.toLowerCase().contains(searchKey.toLowerCase());
    }

    /**
     * searches through the list of products and returns the items with matching names.
     * @param productName
     * @param products
     * @return a list of matching results
     */
    public static List<Product> searchByName(String productName, List<Product> products){
        if(productName == null || productName.isBlank()) return List.of(); //no search if search bar is empty or only has space in it
        List<Product> results = new ArrayList<>();
        for (Product prod: products){
            if(containCharacter(prod.getProductName(), productName)){
                results.add(prod);
            }
        }
        return results;
    }

    /**
     * searches through the list of products and returns the items with matching sizes.
     * @param size
     * @param products
     * @return a list of matching results
     */
    public static List<Product> searchBySize(String size, List<Product> products){
        if(size == null || size.isBlank()) return List.of();
        List<Product> results = new ArrayList<>();
        for (Product prod: products){
            if(containCharacter(prod.getSize(), size)){
                results.add(prod);
            }
        }
        return results;
    }

    /**
     * searches through the list of products and returns the items with matching categories.
     * @param category
     * @param products
     * @return a list of matching results
     */
    public static List<Product> searchByCategory(String category, List<Product> products){
        if(category == null || category.isBlank()) return List.of();
        List<Product> results = new ArrayList<>();
        for (Product prod: products){
            if(containCharacter(prod.getCategory(), category)){
                results.add(prod);
            }
        }
        return results;
    }
}
