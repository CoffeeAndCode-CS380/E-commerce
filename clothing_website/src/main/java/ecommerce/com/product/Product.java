package ecommerce.com.product;

public class Product {
    private String productName;
    private double price;
    private String size;
    private String category;
    private String productID;
    private String imagePath;


    /**
     * contructor
     * @param productName
     * @param price
     * @param size
     * @param category
     * @param productID
     * @param imagePath
     */
    public Product(String productName, double price, String size, String category, String productID, String imagePath) {
        this.productName = productName;
        this.price = price;
        this.size = size;
        this.category = category;
        this.productID = productID;
        this.imagePath = imagePath;
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

    public void setProductID() {
        this.productID = productID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
