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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class mainPageController {

    //declaring all the variables from their fx:id from scene builder here
    @FXML private TextField searchTextField;


    //to use in the openSizeSelectionPage method
    private Scene scene;

    //this method opens the size selection method
    @FXML
    private void openSizeSelectionPage(ActionEvent event, Product product) throws IOException {
        //this should take us from main to size selection page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sizeSelection.fxml"));
        Parent root = loader.load();

        sizeSelectionController controller = loader.getController();
        controller.putProduct(product); //open the size selection page with the picture that we clicked on this is why it takes a product object

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Size Selection"); //title of the page
        stage.show();
    }

    @FXML
    private void openLoginPage(ActionEvent event) throws IOException {
        //this should take us from main to login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.PreviousPageName("mainPage");//trigger the function from login controller to go back to the page
                                                             // that we went to login page from

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    // for each of the pics in main page we set an event that assigns the data related to that product to an object of Product
    // then we call the openSizeSelection method with two arguments the even of clicking on image and the details of that product
    //repeat the same thing for all the products
    //TODO: remember to change the category and price accordingly this is just for test.
    @FXML
    private void clickOnBlackHoodie(ActionEvent event) throws IOException {
        Product blackHoodie = new Product("Black Hoodie", 69,"M","Clothing", "TSH001", "ecommerce/com/site icons/black_hoodie.jpg");
        openSizeSelectionPage(event, blackHoodie);
    }

    @FXML
    private void clickOnGreyHoodie(ActionEvent event) throws IOException {
        Product greyHoodie = new Product("Grey Hoodie", 49.99,"M","Clothing", "TSH001", "ecommerce/com/site icons/grey_hoodie.jpg");
        openSizeSelectionPage(event, greyHoodie);
    }

    @FXML
    private void clickOnWhiteHoodie(ActionEvent event) throws IOException {
        Product whiteHoodie = new Product("White Hoodie", 60,"M","Clothing", "TSH001", "ecommerce/com/site icons/white_hoodie.png");
        openSizeSelectionPage(event, whiteHoodie);
    }

    @FXML
    private void clickOnBlackJeans(ActionEvent event) throws IOException {
        Product blackJeans = new Product("Black Jeans", 33,"M","Clothing", "TSH001", "ecommerce/com/site icons/black_jeans.jpg");
        openSizeSelectionPage(event, blackJeans);
    }

    @FXML
    private void clickOnBlueJeans(ActionEvent event) throws IOException {
        Product blueJeans = new Product("Blue Jeans", 33,"M","Clothing", "TSH001", "ecommerce/com/site icons/blue_jeans.jpg");
        openSizeSelectionPage(event, blueJeans);
    }

    @FXML
    private void clickOnLightBlueJeans(ActionEvent event) throws IOException {
        Product lightBlueJeans = new Product("Light blue Jeans", 33,"M","Clothing", "TSH001", "ecommerce/com/site icons/light_blue_jeans.jpg");
        openSizeSelectionPage(event, lightBlueJeans);
    }

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

    private void openSearchResultPage(List<Product> finalResult){
        try { //open a new scene here that is all
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resultsPage.fxml"));
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
     * handle search by clicking enter
     * @param actionEvent
     */
    @FXML
    public void searchOnEnter(ActionEvent actionEvent){
        handleSearch();
    }

    /**
     * handle search by clicking on magnifying glass
     * @param mouseEvent
     */
    @FXML
    public void searchOnMagnifyingGlass(javafx.scene.input.MouseEvent mouseEvent) {
        handleSearch();
    }
}
