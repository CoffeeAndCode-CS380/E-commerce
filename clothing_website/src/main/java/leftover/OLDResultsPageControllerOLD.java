// src/ecommerce/com/search/ResultsPageController.java
package ecommerce.com.search;

import ecommerce.com.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OLDResultsPageControllerOLD {

    @FXML private ListView<Product> resultsList;

    @FXML
    private void initialize() {
        resultsList.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Product p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty || p == null ? null : p.getProductName());
            }
        });

        resultsList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && !resultsList.getSelectionModel().isEmpty()) {
                openSizeSelection(resultsList.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void setResults(List<Product> products) {
        ObservableList<Product> items = FXCollections.observableArrayList(products);
        resultsList.setItems(items);
    }

    private URL resolve(String... candidates) {
        for (String p : candidates) {
            URL u = getClass().getResource(p);
            if (u != null) return u;
        }
        throw new IllegalStateException("sizeSelection.fxml not found in resources");
    }

    private void openSizeSelection(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(resolve(
                    "/sizeSelection.fxml",
                    "/ecommerce/com/sizeSelection.fxml",
                    "/fxml/sizeSelection.fxml"
            ));
            Parent root = loader.load();

            ecommerce.com.sizeSelectionController controller = loader.getController();
            controller.setProduct(product);

            Stage stage = (Stage) resultsList.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(product.getProductName());
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
