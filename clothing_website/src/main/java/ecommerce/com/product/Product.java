package ecommerce.com.product;

/**
 * this is the mian product class that has the getters and setters
 * The Product class Stores items.
 */
public class Product {
    private String productName;
    private double price;
    private String size;
    private String category;
    private String productID;
    private String imagePath;
    private int quantity;


    /**
     * constructor
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

    /**
     *
     * @return Product Name
     */
    public String getProductName(){
        return productName;
    }

    /**
     * renames product
     *
     * @param productName the new name
     */
    public void setProductName(String productName){
        this.productName = productName;
    }

    /**
     *
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * change  or update product price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return product size
     */
    public String getSize() {
        return size;
    }

    /**
     * set product size
     * @param size new size to be changed
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     *
     * @return Product category
     */

    public String getCategory() {
        return category;
    }

    /**
     * Set the Product category
     * @param category new category
     */

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return Product ID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * sets Product ID
     */
    public void setProductID() {
        this.productID = productID;
    }

    /**
     *
     * @return link or path to the product picture
     */

    public String getImagePath() {
        return imagePath;
    }

    /**
     * set the  path to product picture
     * @param imagePath new pic path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     *
     * @return the number of product in the cart
     */
    public Integer getQuantity() { return quantity; }

    /**
     * set the quantity for the product
     * @param quantity
     */
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
