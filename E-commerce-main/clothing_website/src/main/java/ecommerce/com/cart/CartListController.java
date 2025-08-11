// CartItemController.java
package ecommerce.com.cart;
import ecommerce.com.product.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.function.Consumer;

public class CartListController {
    @FXML private Label nameLabel, priceLabel, qtyLabel;
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
