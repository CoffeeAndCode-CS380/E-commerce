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
    }

    @FXML
     protected void switchToSearchResults() throws IOException{
        App.setRoot("searchResultsPage");
    }

    @FXML
     protected void switchToSizeSelection() throws IOException{
        App.setRoot("sizeSelectionPage");
    }

    @FXML
     protected void switchToLogin() throws IOException{
        App.setRoot("loginPage");
    }
}
