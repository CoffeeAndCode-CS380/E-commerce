package ecommerce.com.search;

import ecommerce.com.product.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchUtils {

    public static List<Product> searchByName(String productName, List<Product> products){
        List<Product> results = new ArrayList<>();
        for (Product prod: products){
            if(productName.equalsIgnoreCase(prod.getProductName())){
                results.add(prod);
            }
        }
        return results;
    }

    public static List<Product> searchBySize(String size, List<Product> products){
        List<Product> results = new ArrayList<>();
        for (Product prod: products){
            if(size.equalsIgnoreCase(prod.getSize())){
                results.add(prod);
            }
        }
        return results;
    }

    public static List<Product> searchByCategory(String category, List<Product> products){
        List<Product> results = new ArrayList<>();
        for (Product prod: products){
            if(category.equalsIgnoreCase(prod.getCategory())){
                results.add(prod);
            }
        }
        return results;
    }
}
