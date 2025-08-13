package ecommerce.com.search;

import ecommerce.com.product.Product;
import ecommerce.com.product.ProductLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * handles search actions or logic
 */

public class OLDSearchController{
    @FXML
    private void handleSearch(ActionEvent event) throws IOException {
        List<Product> searchedProducts = ProductLoader.readFile();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("searchResultPage.fxml"));
        Parent root=loader.load();
        ResultsPageController controller=loader.getController();
        controller.setResults(searchedProducts);
        Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Search Results");
        stage.show();
    }
    //show search result and load fzml

//    /**
//     * loads the search results and pass the product to the page
//     * @param products
//     * @param stage
//     * @throws IOException
//     */
//    public void showSearchResult(List<Product> products,Stage stage)throws IOException{
//      List <Product> productList=
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchResultPage.fxml"));
//        Parent root=loader.load();
//        ResultsPageController controller=loader.getController();
//        controller.setResults(products);
//        stage.setScene(new Scene(root));
//        stage.setTitle("Search Results");
//        stage.show();
//    }









}
