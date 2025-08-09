package ecommerce.com;

import java.io.IOException;
import java.io.InputStream;

import ecommerce.com.login.LoginController;
import ecommerce.com.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class sizeSelectionController {

    //declare variables needed
    @FXML private ImageView ImageOfProduct;
    @FXML private TextField ProductDescription;
    @FXML private TextField ProductPrice;
    @FXML private TextField ProductSize;
    @FXML private Button SizeSmallButton;
    @FXML private Button SizeMediumButton;
    @FXML private Button SizeLargeButton;
    @FXML private Button SizeXLargeButton;

    private Scene scene;

    //I changed CoffeeAndCode to a textfield from a label so this works
    @FXML
    private void backToMainPage(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
    }

    @FXML
    private void openLoginPage(ActionEvent event) throws IOException {
        //this should take us from main to login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController(); //make a controller
        controller.PreviousPageName("sizeSelection"); //trigger the function from login controller to go back to the page
                                                                   // that we went to login page from
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    //a method to display the image on the size selection page based on its image path that we have in the source.
    @FXML
    public void putProduct(Product product){
        String imagePath = product.getImagePath(); //save the image path in a string using the constructor we have.

        // with this line we can load the image
        InputStream imageStream = getClass().getResourceAsStream("/" + imagePath);
        //TODO: this is just a test we can take it out. I put this to see if I'm using the right image path
        if (imageStream == null) {
            System.out.println("Could not find image at: " + imagePath);
            return;
        }

        //setting the image here.
        Image image = new Image(imageStream);
        ImageOfProduct.setImage(image);
        ProductDescription.setText(product.getProductName());
        ProductPrice.setText("$" + Double.toString(product.getPrice())); //make double to string
        ProductSize.setText(product.getSize());
    }

    public void clickOnChangeSize(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if(source == SizeSmallButton){
            ProductSize.setText("S");
        } else if(source == SizeMediumButton) {
            ProductSize.setText("M");
        } else if(source == SizeLargeButton) {
            ProductSize.setText("L");
        } else if(source == SizeXLargeButton) {
            ProductSize.setText("XL");
        }
    }
}