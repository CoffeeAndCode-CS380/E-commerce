package ecommerce.com.product;

public class Product {
    private String productName;
    private double price;
    private String size;
    private String category;
    private String productID;
    private String imagePath;


    public Product(String productName, double price, String size, String category, String productID, String imagePath) {
        this.productName = productName;
        this.price = price;
        this.size = size;
        this.category = category;
        this.productID = productID;
        this.imagePath = imagePath;
    }
    // in ecommerce/com/product/Product.java

    /**
     * Convenience constructor so you can just pass
     * (name, category, price, imagePath) and get
     * default size & productID.
     */
    public Product(String productName,
                   String category,
                   double price,
                   String imagePath) {
        // call your existing 6-arg constructor with dummy size & ID
        this(
                productName,    // name
                price,          // price
                "",             // size (empty for now)
                category,       // category
                "",             // productID (empty for now)
                imagePath       // image path
        );
    }


    public Product() {}

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
