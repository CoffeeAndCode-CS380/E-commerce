package ecommerce.com.cart;

import javafx.fxml.*;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * this class handles the logic for the cart page
 */
public class CartController {


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
        var items = CartUtils.get().getItems();
        cartListView.setItems(items);
        cartListView.setCellFactory(lv -> new CartItemCell());

        cartListView.setItems(CartUtils.get().getItems());
        checkoutButton.disableProperty().bind(Bindings.isEmpty(cartListView.getItems())); //keep the checkout button disable if the cart is empty

        totalCart.textProperty().bind(
                Bindings.createStringBinding(() -> String.format("Total: $%.2f", CartUtils.get().getTotal()), items)
        );
        cartListView.setCellFactory(v -> new CartItemCell());
        cartListView.setFixedCellSize(200);   // length of each last row
        cartListView.setPrefHeight(200 * Math.min(3, cartListView.getItems().size() + 1));
    }

    /**
     * this class works like a constructor for each row of the list in cart.
     * it uses some local variables to make the UI for each row.
     * also allows the user to remove items from cart if needed
     */
    private static final class CartItemCell extends ListCell<Product> {//we are making this method to tell each row in our list how to render
                                                                       // a list cell is a row is drawn in a listView

        //fields needed to make one row of the list
        private final ImageView productImage = new ImageView();
        private final Label productName = new Label();
        private final Label productPrice = new Label();
        private final Label productQuantity = new Label();
        private final Button removeButton = new Button("Remove");

        // made root a vertical card
        private final VBox root = new VBox(8); // spacing

        CartItemCell() {
            // formatting the size of the image
            productImage.setFitWidth(120);
            productImage.setFitHeight(120);
            productImage.setPreserveRatio(true);
            productImage.setSmooth(true);

            // style of the shown text
            productName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            productPrice.setStyle("-fx-font-size: 13px;");
            productQuantity.setStyle("-fx-font-size: 12px; -fx-text-fill: #2a6f6f;");

            // wrapping texts so they show nicely
            productName.setWrapText(true);
            productPrice.setWrapText(true);
            productQuantity.setWrapText(true);

            // information box is a label
            VBox infoBox = new VBox(4, productName, productPrice, productQuantity);
            infoBox.setAlignment(Pos.TOP_CENTER);

            // make the width of the button full and keep it at the bottom of the item
            removeButton.setMaxWidth(Double.MAX_VALUE);
            infoBox.setAlignment(Pos.CENTER);

            // style of the cards
            root.setPadding(new Insets(10));
            root.setAlignment(Pos.TOP_CENTER);
            root.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 10;
            -fx-border-color: rgba(0,0,0,0.08);
            -fx-border-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);
        """);

            root.getChildren().addAll(productImage, infoBox, removeButton);

            // Let the card stretch to the ListView width
            // grows as more items get added
            root.setMaxWidth(Double.MAX_VALUE);

            // logic of clicking on the remove button
            removeButton.setOnAction(e -> {
                var prod = getItem();
                if (prod != null) {
                    CartUtils.get().removeItem(prod);
                }
            });
        }

        /**
         * this method places the picture and product information of the selected item in the cart
         * @param prod
         * @param empty
         */
        @Override
        protected void updateItem(Product prod, boolean empty) {
            super.updateItem(prod, empty);

            if (empty || prod == null) {
                setText(null);
                setGraphic(null);
                return;
            }

            // Make card follow list width
            if (getListView() != null && root.prefWidthProperty().get() == 0) {
                root.prefWidthProperty().bind(getListView().widthProperty().subtract(24)); // padding for scrollbar
            }

            productImage.setImage(null);
            try {
                var inputStream = getClass().getResourceAsStream("/" + prod.getImagePath());
                if (inputStream != null) {
                    productImage.setImage(new Image(inputStream));
                }
            } catch (Exception ignored) {}

            // text for the labes in the cart
            productName.setText(prod.getProductName() + " (" + prod.getSize() + ")");
            productPrice.setText(String.format("$%.2f", prod.getPrice()));
            productQuantity.setText("Quantity: " + Math.max(1, prod.getQuantity())); // shows 1+ safely

            setText(null);
            setGraphic(root);
        }
}

    /**
     * this method takes the user from the cart page to the checkout page by clicking on the checkout button in cart page
     * @param event
     * @throws IOException
     */
    @FXML
    private void clickOnCheckoutButton(javafx.event.ActionEvent event) throws IOException {

        if(cartListView.getItems() == null || cartListView.getItems().isEmpty()){ //checks if the cart is empty
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecommerce/com/checkout.fxml"));
        Parent root = loader.load();

        //move to the new scene
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Checkout");
        stage.show();
    }
}