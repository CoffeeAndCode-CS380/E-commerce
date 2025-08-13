// CartItemController.java
package ecommerce.com.cart;

import ecommerce.com.product.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import java.util.function.Consumer;

//TODO: delete this later
public class CartItemController {

    @FXML private Label nameLabel, priceLabel, qtyLabel;
    @FXML private ImageView ImageOfProduct;
    @FXML private Button removeButton;

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

}