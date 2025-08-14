package ecommerce.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import ecommerce.com.login.LoginController;
import ecommerce.com.product.Product;
import ecommerce.com.product.ProductLoader;
import ecommerce.com.search.ResultsPageController;
import ecommerce.com.search.SearchUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * this class handle the logic related to the navigation bar. Allowing users to transition between website pages.
 */
public class NavigationBarController{

    //declaring all the variables from their fx:id from scene builder here
    @FXML private TextField searchTextField;

    //to use in the openSizeSelectionPage method
    private Scene scene;

    /*@FXML
    private void switchToMain() throws IOException{
        App.setRoot("mainPage");
        System.out.println("TESTING");
    }*/

    /**
     * Switches to cart page by clicking on the cart icon
     * @param event
     * @throws IOException
     */
    @FXML
    protected void switchToCart(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cartPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cart");
        stage.show();
    }

    /*@FXML
    protected void switchToSearchResults() throws IOException{
        App.setRoot("searchResultsPage");
    }*/

    /*@FXML
    protected void switchToLogin(ActionEvent event) throws IOException{
        App.setRoot("loginPage");
    }*/

    /**
     * executes search by hitting enter
     * @param actionEvent
     */
    @FXML
    public void searchOnEnter(ActionEvent actionEvent){
        handleSearch();
    }

    /**
     * executes search by clicking on magnifying glass with mouse
     * @param mouseEvent
     */
    @FXML
    public void searchOnMagnifyingGlass(javafx.scene.input.MouseEvent mouseEvent) {
        handleSearch();
    }

    /**
     * uses the search methods in SearchUtils class, creates 3 lists and then merges them into one result list
     * 3 lists (resultByName, resultBySize, resultByCategory) into -> finalResult
     *
     */
    @FXML
    public void handleSearch(){
        String searchKey = searchTextField.getText().trim();

        if(searchKey.isEmpty()){
            return;
        }

        // load all the products using the method in product loader into a single list
        java.util.List<Product> allProducts = ProductLoader.readFile();

        //again make more lists and assign the result by each category accordingly
        java.util.List<Product> resultByName = SearchUtils.searchByName(searchKey, allProducts); // the searches get two arguments
        java.util.List<Product> resultBySize = SearchUtils.searchBySize(searchKey, allProducts);
        java.util.List<Product> resultByCategory = SearchUtils.searchByCategory(searchKey, allProducts);

        //merge all the results that are divided by category into one list
        Set<Product> mergeResults = new LinkedHashSet<>();
        mergeResults.addAll(resultByName); //argument is the list that we made above
        mergeResults.addAll(resultBySize);
        mergeResults.addAll(resultByCategory);

        //put them in a list called final result that has all the related results
        List<Product> finalResult = new ArrayList<>(mergeResults);
        openSearchResultPage(finalResult);
    }

    /**
     * based on the results of the search opens the search result page after hitting enter or clicking on the magnifying glass.
     * @param finalResult
     */
    private void openSearchResultPage(List<Product> finalResult){
        try { //open a new scene here that is all
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchResultsPage.fxml"));
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

    /**
     * opens the login page by clicking on the login button in navigation bar
     * @param event
     * @throws IOException
     */
    @FXML
    private void openLoginPage(ActionEvent event) throws IOException {
        //this should take us from main to login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();

        //TODO: this line is the issues that mainPage always get open after clicking back
        LoginController controller = loader.getController();
        controller.PreviousPageName("mainPage");//trigger the function from login controller to go back to the page
        // that we went to login page from

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    /**
     * takes you back to the main page by clicking on CoffeeAndCode icon
     * @param event
     * @throws IOException
     */
    @FXML
    private void backToMainPage(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
        //I changed CoffeeAndCode to a textfield from a label so this works
        // this just takes you back to main page from size selection by clicking the logo
    }
}