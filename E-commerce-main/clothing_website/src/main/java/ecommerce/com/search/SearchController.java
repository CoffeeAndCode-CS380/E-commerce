package ecommerce.com.search;

import ecommerce.com.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ecommerce.com.search.ItemRepository;
import ecommerce.com.search.SearchUtils;
import ecommerce.com.product.Product;
import javafx.event.ActionEvent;


import java.util.List;

import javafx.scene.control.ListView;


public class SearchController {

    /* */
    @FXML
    private TextField mainSearchTextField;
    // ← Add this:
    @FXML
    private ListView<Product> searchResultsList;


    @FXML
    public void initialize() {
        //String query = mainSearchTextField.getText();
        //TODO: REIMPLEMNT CODE
        // Listen for *any* key press on that field:
//        mainSearchTextField.addEventHandler(KeyEvent.KEY_PRESSED, evt -> {
//            // If it was the Enter key, call your search method:
//            if (evt.getCode() == KeyCode.ENTER) {
//                onSearch(new ActionEvent(mainSearchTextField, null));
//                evt.consume();  // don’t let anything else eat this key press
//            }
//        });
        // When *any* key is pressed…
//        mainSearchTextField.setOnKeyPressed(evt -> {
//            // …check it’s the ENTER key…
//            if (evt.getCode() == KeyCode.ENTER) {
//                // …and invoke your search logic
//                onSearch(new ActionEvent(mainSearchTextField, null));
//                evt.consume(); // stop further processing
//            }
//        });
        if (mainSearchTextField != null) {
            mainSearchTextField.setOnKeyPressed(evt -> {
                if (evt.getCode() == KeyCode.ENTER) {
                    onSearch(new ActionEvent(mainSearchTextField, null));
                    evt.consume();
                }
            });
        }
    }
    @FXML
    private void onSearch(ActionEvent event) {
        String query = mainSearchTextField.getText();
        // 2. Grab every product from your “catalog”
        List<Product> allProducts = ItemRepository.getAllProducts();

        // 3. Filter to only those whose name contains the query
        List<Product> matches = SearchUtils.searchByName(query, allProducts);

        // 4. Update your ListView (assumes fx:id="searchResultsList")
        searchResultsList.getItems().setAll(matches);


}
    }