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

/**
 * show products in a list and allow user to select size
 */

public class ResultsPageController {

    @FXML
    private ListView<Product> resultsList;

    /**
     * called after fxml is loaded
     */

    @FXML
    private void initialize() {
        resultsList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Product p, boolean empty) {
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

    /**
     * updates the list view
     *
     * @param products the list of product to show
     */
    public void setResults(List<Product> products) {
        ObservableList<Product> items = FXCollections.observableArrayList(products);
        resultsList.setItems(items);
    }

    /**
     * find match fxml file from given path
     *
     * @param candidates file paths
     * @return the path of the found resource
     */
    private URL resolve(String... candidates) {
        for (String p : candidates) {
            URL u = getClass().getResource(p);
            if (u != null) return u;
        }
        throw new IllegalStateException("sizeSelection.fxml not found in resources");
    }

    /**
     * allows user to select size for the chosen product
     *
     * @param product current selected product
     */
    private void openSizeSelection(Product product) {
        try {
            //load fxml
            FXMLLoader loader = new FXMLLoader(resolve(
                    "/sizeSelection.fxml",
                    "/ecommerce/com/sizeSelection.fxml",
                    "/fxml/sizeSelection.fxml"
            ));
            Parent root = loader.load();
//get controller for size selection
            ecommerce.com.sizeSelectionController controller = loader.getController();
            controller.putProduct(product);
//switch to new scne
            Stage stage = (Stage) resultsList.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(product.getProductName());
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}