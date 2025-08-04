package ecommerce.com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import ecommerce.com.cart.Product;
import ecommerce.com.cart.ProductCatalog;

public class SecondaryController {
    // 1️⃣ Inject a ListView (or whatever UI) in your secondary.fxml
    @FXML
    private ListView<Product> productListView;

    // 2️⃣ Hold onto the catalog
    private ProductCatalog catalog;

    /** Called by App.loadViewWithCatalog(...) */
    public void setCatalog(ProductCatalog catalog) {
        this.catalog = catalog;
        // e.g. show the same product list (or maybe cart contents)
        productListView.setItems(
                FXCollections.observableArrayList(catalog.getProducts())
        );
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
