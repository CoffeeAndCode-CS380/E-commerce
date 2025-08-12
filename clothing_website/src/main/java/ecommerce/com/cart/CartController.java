package ecommerce.com.cart;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;
import ecommerce.com.product.Product;

public class CartController {

    @FXML private ListView<Product> cartListView;
    @FXML private Label totalCart;

    @FXML private void initialize() {
        var items = CartUtils.get().getItems();
        cartListView.setItems(items);
        cartListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Product p, boolean e){
                super.updateItem(p,e);
                setText(e || p==null ? null : p.getProductName()+" ("+p.getSize()+") — $"+p.getPrice());
            }
        });
        totalCart.textProperty().bind(
                Bindings.createStringBinding(() -> String.format("Total: $%.2f", CartUtils.get().getTotal()), items)
        );
    }


}