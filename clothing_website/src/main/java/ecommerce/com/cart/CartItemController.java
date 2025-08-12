// CartItemController.java
package ecommerce.com.cart;

import ecommerce.com.product.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import java.util.function.Consumer;

public class CartItemController {

    @FXML private Label nameLabel, priceLabel, qtyLabel;
    @FXML private ImageView ImageOfProduct;

    private Product product;
    private Consumer<Product> onRemove = p -> {};

    void setData(Product p) {
        product = p;
        nameLabel.setText(p.getProductName());
        priceLabel.setText(String.format("$%.2f", p.getPrice()));
        qtyLabel.setText("Qty: 1"); // adjust if you track qty
    }

    void setOnRemove(Consumer<Product> h) { onRemove = h; }
    @FXML private void remove() { onRemove.accept(product); }

    /*@FXML
    public void putProduct(Product product){
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
       *//* ProductDescription.setText(product.getProductName());
        ProductPrice.setText("$" + Double.toString(product.getPrice())); //make double to string
        ProductSize.setText(product.getSize());*//*
    }*/

}