//package ecommerce.com;
//
//import java.io.IOException;
//import javafx.fxml.FXML;
//
//public class PrimaryController {
//
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
//}
package ecommerce.com;

import ecommerce.com.search.ItemRepository;
import ecommerce.com.search.SearchUtils;
import ecommerce.com.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;   // or GridPane, depending on your layout
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.List;

public class PrimaryController {

    // must match fx:id in mainPage.fxml
    @FXML private TextField mainSearchTextField;

    // if you’re showing results in a ListView
    @FXML private ListView<Product> searchResultsList;

    @FXML
    private void initialize() {
        // Optional: also wire Enter explicitly in code
        //mainSearchTextField.setOnAction(this::onSearch);
        //mainSearchTextField.setOnAction(evt -> onSearch(evt));
        if (mainSearchTextField != null) {
            mainSearchTextField.setOnKeyPressed(evt -> {
                if (evt.getCode() == KeyCode.ENTER) {
                    onSearch(new ActionEvent(mainSearchTextField, null));
                    evt.consume();
                }
            });
        }
    }

    /** Called when user presses Enter in the TextField or clicks your Search button */
    @FXML
    private void onSearch(ActionEvent event) {
        // 1. Read their query
        String query = mainSearchTextField.getText().trim();

        // 2. Fetch all products
        List<Product> all = ItemRepository.getAllProducts();

        // 3. Filter
        List<Product> matches = SearchUtils.searchByName(query, all);

        // 4. Show results
        searchResultsList.getItems().setAll(matches);
    }
}

