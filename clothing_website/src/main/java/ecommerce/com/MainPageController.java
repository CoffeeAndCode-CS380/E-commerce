package ecommerce.com;

import java.io.IOException;

import javafx.fxml.FXML;

/**
 * Main page will be the main directory for the user to navigate to other pages.
 * Paths established
 * -MainPage -> Login
 * -MainPage -> MainPage (verify this properly refreshes)
 */
public class MainPageController extends NavigationBarController{


    //grab the fxml AND its controller
    //then give a reference to mainPageController
    

    //FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage"));
    //Parent root = loader.load();
    //MainPageController otherController = loader.getController();
    //MainPageController. setMainController(this);
    
        @FXML
     protected void switchToSizeSelection() throws IOException{
        App.setRoot("sizeSelection");
    }
    

}
