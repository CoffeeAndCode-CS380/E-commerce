package ecommerce.com;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavigationBarController {

    @FXML
     private void switchToMain() throws IOException{
        App.setRoot("mainPage");
        System.out.println("TESTING");
    }
    
    @FXML
     protected void switchToCart() throws IOException{
        App.setRoot("cartPage");
        System.out.println("CART PAGE");
    }

    @FXML
     protected void switchToSearchResults() throws IOException{
        App.setRoot("searchResultsPage");
        System.out.println("SEARCH RESULTS");
    }

    @FXML
     protected void switchToLogin() throws IOException{
        App.setRoot("loginPage");
        System.out.println("LOGIN");
    }
}
