package ecommerce.com;

import java.io.IOException;

import ecommerce.com.cart.Product;
import ecommerce.com.cart.ProductCatalog;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PrimaryController {
    @FXML
    private ListView<Product> productListView;

    // 2️⃣ Hold onto the catalog once App wires it in
    private ProductCatalog catalog;

    /** Called by App.loadViewWithCatalog(...) */
    public void setCatalog(ProductCatalog catalog) {
        this.catalog = catalog;
        // populate the ListView with all available products
        productListView.setItems(
                FXCollections.observableArrayList(catalog.getProducts())
        );
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
