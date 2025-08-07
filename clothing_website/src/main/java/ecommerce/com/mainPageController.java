package ecommerce.com;

import java.io.IOException;

import ecommerce.com.product.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class mainPageController {

    //declaring all the variables from their fx:id from scene builder here
    @FXML private Button blackHoodieButton;
    @FXML private Button grayHoodieButton;
    @FXML private Button whiteHoodieButton;
    @FXML private Button blackJeansButton;
    @FXML private Button blueJeansButton;
    @FXML private Button lightBlueJeansButton;

    //to use in the openSizeSelectionPage method
    private Scene scene;


    //this method opens the size selection method
    @FXML
    private void openSizeSelectionPage(ActionEvent event, Product product) throws IOException {
        //this should take us from main to size selection page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sizeSelection.fxml"));
        Parent root = loader.load();

        sizeSelectionController controller = loader.getController();
        controller.putProduct(product);

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Size Selection");
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

    //Is this efficient tho???
}
