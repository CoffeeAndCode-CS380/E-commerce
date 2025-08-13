package ecommerce.com;

import java.io.InputStream;
import ecommerce.com.cart.CartUtils;
import ecommerce.com.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * this class handles all the logic related to the size selection page
 */
public class sizeSelectionController {

    //declare variables needed
    @FXML private ImageView ImageOfProduct;
    @FXML private TextField ProductDescription;
    @FXML private TextField ProductPrice;
    @FXML private TextField ProductSize;
    @FXML private Button SizeSmallButton;
    @FXML private Button SizeMediumButton;
    @FXML private Button SizeLargeButton;
    @FXML private Button SizeXLargeButton;
    @FXML private Button addToCartButton;

    private Scene scene;
    private Product currentProduct;
    private String selectedSize;

    /**
     * put the picture of the selected item in the imageview of size selection page
     * @param product
     */
    @FXML
    public void putProduct(Product product){ //a method to display the image on the size selection page based
                                                     // on its image path that we have in the source.
        currentProduct = product;
        selectedSize = product.getSize();
        String imagePath = product.getImagePath(); //save the image path in a string using the constructor we have.

        // with this line we can load the image
        InputStream imageStream = getClass().getResourceAsStream("/" + imagePath);

        //TODO: this is just a test we can take it out. I put this to see if I'm using the right image path
        if (imageStream == null) {
            System.out.println("Could not find image at: " + imagePath);
            return;
        }

        //setting the image here.
        Image image = new Image(imageStream);
        ImageOfProduct.setImage(image);
        ProductDescription.setText(product.getProductName());
        ProductPrice.setText("$" + Double.toString(product.getPrice())); //make double to string
        ProductSize.setText(selectedSize);
    }

    /**
     * changes the size of the clothes by clicking on the buttons related to size
     * four options (S, M, L, XL)
     * @param event
     */
    public void clickOnChangeSize(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if(source == SizeSmallButton){
            selectedSize = "S";
        } else if(source == SizeMediumButton) {
            selectedSize = "M";
        } else if(source == SizeLargeButton) {
            selectedSize = "L";
        } else if(source == SizeXLargeButton) {
            selectedSize = "XL";
        }
        ProductSize.setText(selectedSize); //keeps the size dynamic and not just medium anymore
    }

    /**
     * button that adds the selected item to the cart
     * @param event
     */
    @FXML
    public void clickOnAddToCart(ActionEvent event){
        Product selectedProduct = new Product(currentProduct.getProductName(),
                                              currentProduct.getPrice(),
                                              selectedSize, // the dynamic size
                                              currentProduct.getCategory(),
                                              currentProduct.getProductID(),
                                              currentProduct.getImagePath());
        putProduct(selectedProduct);
        CartUtils.get().addItem(selectedProduct);
    }
}