package ecommerce.com.search;

import ecommerce.com.SizeSelectionController;
import ecommerce.com.product.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SearchController {


    @FXML private void openSizeSelection(Product p) throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/ecommerce/com/sizeSelection.fxml"));
        Parent root = fx.load();
        SizeSelectionController c = fx.getController();
        c.setProduct(p);
        new Stage(){ { setScene(new Scene(root)); setTitle("Choose size"); } }.show();
    }




}
