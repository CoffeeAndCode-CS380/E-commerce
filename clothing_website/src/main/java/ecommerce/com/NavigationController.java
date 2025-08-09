package ecommerce.com;

import java.io.IOException;

public class NavigationController {
    
    protected void switchToMain() throws IOException{
        App.setRoot("mainPage");
    }
    
    protected void switchToCart() throws IOException{
        App.setRoot("cartItem");
    }

    protected void switchToSearchResults() throws IOException{
        App.setRoot("SearchResultsPage");
    }

    protected void switchToSizeSelection() throws IOException{
        App.setRoot("sizeSelectionPage");
    }

    protected void switchToLogin() throws IOException{
        App.setRoot("loginPage");
    }
}
