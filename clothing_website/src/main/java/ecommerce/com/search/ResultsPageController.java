
package ecommerce.com.search;

import ecommerce.com.product.Product;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

/**
 * this class handles the logic of the search result page
 */
public class ResultsPageController {

    @FXML
    private ListView<String> resultsList;

    /**
     * based on the results of the search, places the items in a list view.
     * @param products
     */
    public void setResults(List<Product> products) {
        ObservableList<String> productNames = FXCollections.observableArrayList();

        for(Product product : products){
            productNames.add(product.getProductName());
        }
        resultsList.setItems(productNames);
    }
}



