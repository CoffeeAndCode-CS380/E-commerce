package ecommerce.com;

import java.io.IOException;
import ecommerce.com.cart.CartController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**Due to the linking process required by Scenebuilder/javafx this extended class is
 * needed to link functionality to new elements on the cartPage fxml
 */
public class cartPageController extends CartController {

    @FXML private TextField addressTextField;

    private Scene scene;

    @FXML
    private void goToCheckout(javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkout.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Checkout");
        stage.show();
    }
}