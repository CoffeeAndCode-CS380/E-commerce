package ecommerce.com.product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {
    public static List<Product> readFile(){
        List<Product> products = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); //split contents of each line by comma
                if (parts.length == 6){
                    String productName = parts[0].trim(); //part 1 name and so on
                    double price = Double.parseDouble(parts[1].trim());
                    String size = parts[2].trim();
                    String category = parts[3].trim();
                    String productID = parts[4].trim();
                    String imagePath = parts[5].trim();

                    Product product = new Product(productName, price, size, category, productID, imagePath); //cretae a product object
                    products.add(product); //add them to the object
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void fileParser(){
        List<Product> products = readFile();
        for (Product product : products){ //read contents of the product list
            // do something
        }
    }

}
