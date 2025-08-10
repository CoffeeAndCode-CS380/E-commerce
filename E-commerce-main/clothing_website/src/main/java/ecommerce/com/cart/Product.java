package ecommerce.com.cart;

import java.util.ArrayList;
// items to prrch
public class Product {
    private String id;
    private String name;
    private double price;

    /**
     *
     * @param id
     * @param name
     * @param price
     */

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    /**
     * @return id
     *
     */
    public String getId(){
        return id;
    }

    public String getName(){
        return name;

    }
    public double getPrice(){
        return price;
    }

}