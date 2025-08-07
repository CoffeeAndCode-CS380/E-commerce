package ecommerce.com.search;

import ecommerce.com.product.Product;
import ecommerce.com.product.ProductLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class SearchController {

    @FXML
    private TextField searchTextField;


    public void handleSearch(){
        String searchKey = searchTextField.getText().trim();

        if(searchKey.isEmpty()){ //TODO: change this so if they click on search when the field is empty doesn't do anything
            //just put a message here maybe
            return;
        }

        // load all the products using the method in product loader into a single list
        List<Product> allProducts = ProductLoader.readFile();

        //again make more lists and assign the result by each category accordingly
        List<Product> resultByName = SearchUtils.searchByName(searchKey, allProducts); // the searches get two arguments
        List<Product> resultBySize = SearchUtils.searchBySize(searchKey, allProducts);
        List<Product> resultByCategory = SearchUtils.searchByCategory(searchKey, allProducts);

        //merge all the results that are divided by category into one list
        Set<Product> mergeResults = new LinkedHashSet<>();
        mergeResults.addAll(resultByName); //argument is the list that we made above
        mergeResults.addAll(resultBySize);
        mergeResults.addAll(resultByCategory);

        //put them in a list called final result that has all the related results
        List<Product> finalResult = new ArrayList<>(mergeResults);

    try { //open a new scene here that is all
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/search/resultsPage.fxml"));
            Parent root = loader.load();

            ResultsPageController controller = loader.getController();
            controller.setResults(finalResult);

            //open a new scene
            Stage stage = new Stage();
            stage.setTitle("Search results");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    private void handleSearchClick(MouseEvent event){
        handleSearch();
    }

}
