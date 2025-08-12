package ecommerce.com.search;

import ecommerce.com.product.Product;
import ecommerce.com.product.ProductLoader;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class SearchTest {

    public static void main(String[] args) {
        // Load all products from file
        List<Product> allProducts = ProductLoader.readFile();

        // Simulate a search
        String searchKey = "accessories"; // Try "Sneakers", "M", "Accessories", etc.

        // Call all search methods
        List<Product> resultByName = SearchUtils.searchByName(searchKey, allProducts);
        List<Product> resultBySize = SearchUtils.searchBySize(searchKey, allProducts);
        List<Product> resultByCategory = SearchUtils.searchByCategory(searchKey, allProducts);

        // Merge results using a Set to avoid duplicates
        Set<Product> mergedResults = new LinkedHashSet<>();
        mergedResults.addAll(resultByName);
        mergedResults.addAll(resultBySize);
        mergedResults.addAll(resultByCategory);

        // Final result as a list
        List<Product> finalResults = new ArrayList<>(mergedResults);

        // Print the results
        System.out.println("Search results for: \"" + searchKey + "\"");
        if (finalResults.isEmpty()) {
            System.out.println("No matching products found.");
        } else {
            for (Product product : finalResults) {
                System.out.println(product.getProductName() + " - " + product.getCategory() + " - " + product.getSize());
            }
        }
    }
}
