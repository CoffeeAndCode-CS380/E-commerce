package ecommerce.com.ui;

import ecommerce.com.product.Product;
import ecommerce.com.product.ProductLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class ProductsController {

    @FXML private ListView<Product> productsList;

    @FXML
    public void initialize() {
        productsList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Product p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty || p == null ? null : p.getProductName() + " — $" + p.getPrice());
            }
        });

        try {
            List<Product> items = ProductLoader.readFile("products.txt");
            productsList.getItems().setAll(items);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Could not load products.txt").showAndWait();
        }
    }
}
