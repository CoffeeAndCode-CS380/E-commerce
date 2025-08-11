package ecommerce.com.product;

// items to prrch
public class Product1 {
    private String id;
    private String name;
    private double price;

    /**
     *
     * @param id
     * @param name
     * @param price
     */

    public Product1(String id, String name, double price) {
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