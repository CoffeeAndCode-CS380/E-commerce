package ecommerce.com.cart;

import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;
import ecommerce.com.product.Product;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * this class handles javafx logic for the cart page
 */
public class CartController {
    /**
     * variables for class
     */

    @FXML private ListView<Product> cartListView;
    @FXML private Label totalCart;
    @FXML private Button checkoutButton;

    private Scene scene;



    /**
     * Initializes the cart view when fxml loads.
     * it loads the cart items in to display list and formates them
     *
     */
    @FXML
    private void initialize() {
        var items = CartUtils.get().getItems(); //get cart
        cartListView.setItems(items); //show items in list view
        cartListView.setCellFactory(lv -> new CartItemCell()); //use layout for each item
//update total labels s cart items changes
        totalCart.textProperty().bind(
                Bindings.createStringBinding(() -> String.format("Total: $%.2f", CartUtils.get().getTotal()), items)
        );
    }

    /**
     * this class works like a constructor for each row of the list in cart.
     * it uses some local variables to make the UI for each row.
     * also allows the user to remove items from cart if needed
     */
    private static final class CartItemCell extends ListCell<Product> {//we are making this method to tell each row in our list how to render
                                                                       // a list cell is a row is drawn in a listView
        //these fields are the small UIs that make one row
        private final ImageView productImage = new ImageView();
        private final Label productName = new Label();
        private final Label productPrice = new Label();
        private final Label productQuantity = new Label();
        private final Button removeButton = new Button("Remove");
        private final HBox root = new HBox(10); //number of spacing pixels between items

        //constructor: build one row once and use it more
        CartItemCell() {
            productImage.setFitHeight(64);
            productImage.setFitWidth(64);
            productImage.setPreserveRatio(true);

            //making a vertical box with 3 variables in it that are separated by 4 pixel from each other
            var textBox = new VBox(4, productName, productPrice, productQuantity);

            //aligning the picture
            root.getChildren().addAll(productImage, textBox, removeButton);
            root.setAlignment(Pos.CENTER);

            //once the remove button is pressed, run the code inside ()
            removeButton.setOnAction( e -> {
                var prod = getItem();
                if (prod != null){ // if cart is not empty remove the item once the button is clicked
                    CartUtils.get().removeItem(prod);
                }
            });
        }

        /**
         * this method places the picture and product information of the selected item in the cart
         * @param prod the {@link Product} items to be displayed
         * @param  empty the empty
         */
        @Override
        protected void updateItem(Product prod, boolean empty){
            super.updateItem(prod, empty);

            if(prod == null || empty){ // if there are no items in the cart keep them null to avoid weird graphics
                setText(null);
                setGraphic(null);
                return;
            }

            //putting the image of the clothes there in the cart
            productImage.setImage(null);
            try{
                var inputStream = getClass().getResourceAsStream("/" + prod.getImagePath());
                if (inputStream != null){
                    productImage.setImage(new Image(inputStream));
                }
            } catch (Exception ignored) {}

            //setting the text of labes here
            productName.setText(prod.getProductName() + " (" + prod.getSize() + ")");
            productPrice.setText(String.format("$%.2f",prod.getPrice()));
            productQuantity.setText("Quantity: " + (prod.getQuantity() != null ? prod.getQuantity() : 1));

            setText(null);
            setGraphic(root);
        }
    }

    /**
     * this method takes the user from the cart page to the checkout page by clicking on the checkout button in cart page
     * @param event tricked when clicking checkout button
     * @throws IOException if the FXML file {@code checkout.fxml}cannot be loaded
     */
    @FXML
    private void clickOnCheckoutButton(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecommerce/com/checkout.fxml"));
        Parent root = loader.load();

        CartController controller = loader.getController();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Checkout");
        stage.show();
    }
}