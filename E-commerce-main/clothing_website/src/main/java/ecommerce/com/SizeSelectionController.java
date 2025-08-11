package ecommerce.com;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import ecommerce.com.cart.CartUtils;      // or use Session.CURRENT_USER
import ecommerce.com.product.Product;

public class SizeSelectionController {
    @FXML private ComboBox<String> sizeBox;
    private Product product;

    public void setProduct(Product p) { this.product = p; }

    @FXML private void addToCart() {
        if (product == null) return;
      //  String size = (sizeBox != null) ? sizeBox.getValue() : null; // optional
        product.setSize(sizeBox.getValue());
        CartUtils.get().addItem(product);       // or: Session.CURRENT_USER.getCart().addItem(product);
        sizeBox.getScene().getWindow().hide();  // close dialog
    }
}
