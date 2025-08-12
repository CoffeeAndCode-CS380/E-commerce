package ecommerce.com;

import java.io.IOException;
import java.io.InputStream;
import ecommerce.com.cart.CartUtils;
import ecommerce.com.login.LoginController;
import ecommerce.com.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

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

    /**
     * put the picture of the selected item in the image box of size selection page
     * @param product
     */
    //a method to display the image on the size selection page based on its image path that we have in the source.
    @FXML
    public void putProduct(Product product){

        currentProduct = product;
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
        ProductSize.setText(product.getSize());
    }

    /**
     * changes the size of the clothes
     * @param event
     */
    public void clickOnChangeSize(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if(source == SizeSmallButton){
            ProductSize.setText("S");
        } else if(source == SizeMediumButton) {
            ProductSize.setText("M");
        } else if(source == SizeLargeButton) {
            ProductSize.setText("L");
        } else if(source == SizeXLargeButton) {
            ProductSize.setText("XL");
        }
    }

    @FXML
    public void clickOnAddToCart(ActionEvent event){
        Product selectedProduct = currentProduct;
        putProduct(selectedProduct);
        CartUtils.get().addItem(selectedProduct);
    }
}