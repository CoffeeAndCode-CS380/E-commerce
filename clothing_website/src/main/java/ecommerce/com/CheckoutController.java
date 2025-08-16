package ecommerce.com;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/** This class handles all log for the checkout page and its UI elements. */
public class CheckoutController {

    @FXML private ChoiceBox<String> stateChoiceBox;
    @FXML private TextField addressTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField cardNumTextField;
    @FXML private TextField nameOnCardTextField;
    @FXML private TextField billingZipTextField;
    @FXML private TextField cvvTextField;
    @FXML private Button payNowButton;
    private final Random rand = new Random();

    /**This method gives the state choice box its 50 items/states for the user to pick from. */
    @FXML
    private void initializeChoiceBox() {
        stateChoiceBox.setOnMouseClicked(event -> {
            if (stateChoiceBox.getItems().isEmpty()) { // populate only once
                stateChoiceBox.getItems().addAll(
                        "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                        "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
                        "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                        "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
                        "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
                        "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                        "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
                        "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
                        "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                        "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
                );
            }
        });
    }
//---------------------------------------------------------------------------------------------------------------
    /**
     * This method validates user entered zipcode on 'enter' and re-validates if the textfield loses focus.
     * @param event
     */
    @FXML
    private void validateZipCode(ActionEvent event) {
        zipCodeLength(); // still works on Enter

        // Attach focus listener the first time this method runs
        if (zipCodeTextField.getProperties().get("listenerAdded") == null) {
            zipCodeTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) { // lost focus
                    zipCodeLength();
                }
            });
            zipCodeTextField.getProperties().put("listenerAdded", true);
        }
    }
    /**
     * This method checks the validity of user zip code input, should be 5 digits.
     * @return boolean
     */
    @FXML
    private boolean zipCodeLength(){
        String zipCode = zipCodeTextField.getText();
        boolean inputValid = false;
        Alert zipCodeAlert;
        if(!(zipCode.matches("\\d{5}"))){ //force user to have a 5 digit zipcode
            zipCodeAlert = new Alert(Alert.AlertType.ERROR);
            zipCodeAlert.setTitle("ZIP CODE ERROR");
            zipCodeAlert.setHeaderText(null);
            zipCodeAlert.setContentText("Bad zip code, must be 5 digits.");
            zipCodeAlert.showAndWait();
        }
        else{
            inputValid = true;
        }
        return inputValid;
    }
//---------------------------------------------------------------------------------------------------------------
    /**
     * This method validates user entered card number on 'enter' and re-validates if the textfield loses focus.
     * @param event
     */
    @FXML
    private void validateCardNumber(ActionEvent event) {
        cardNumberLength(); // still works on Enter

        // Attach focus listener the first time this method runs
        if (cardNumTextField.getProperties().get("listenerAdded") == null) {
            cardNumTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) { // lost focus
                    cardNumberLength();
                }
            });
            cardNumTextField.getProperties().put("listenerAdded", true);
        }
    }

    /**
     * This method checks the validity of user card number input, should be 16 digits(no spaces).
     * @return boolean
     */
    @FXML
    private boolean cardNumberLength(){
        String cardNumber = cardNumTextField.getText();
        boolean inputValid = false;
        Alert cardNumberAlert;
        if(!(cardNumber.matches("\\d{16}"))){ //force user to have a 16 digit card number
            cardNumberAlert = new Alert(Alert.AlertType.ERROR);
            cardNumberAlert.setTitle("CARD NUMBER ERROR");
            cardNumberAlert.setHeaderText(null);
            cardNumberAlert.setContentText("Bad card number, must be 16 digits.");
            cardNumberAlert.showAndWait();
        }
        else{
            inputValid = true;
        } 
        return inputValid;
    }
//---------------------------------------------------------------------------------------------------------------

    /**
     * This method validates user entered cvv on 'enter' and re-validates if the textfield loses focus.
     * @param event
     */
    @FXML
    private void validateCVV(ActionEvent event) {
        cvvLength(); // still works on Enter

        // Attach focus listener the first time this method runs
        if (cvvTextField.getProperties().get("listenerAdded") == null) {
            cvvTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) { // lost focus
                    cvvLength();
                }
            });
            cvvTextField.getProperties().put("listenerAdded", true);
        }
    }

    /**
     * This method checks the validity of user cvv input, should be three digits.
     * @return boolean
     */
    @FXML
    private boolean cvvLength(){
        String cvv = cvvTextField.getText();
        boolean inputValid = false;
        Alert cvvAlert;
        if(!(cvv.matches("\\d{3}")) ){ //force user to have a 3 digit cvv
            cvvAlert = new Alert(Alert.AlertType.ERROR);
            cvvAlert.setTitle("CVV ERROR");
            cvvAlert.setHeaderText(null);
            cvvAlert.setContentText("Bad CVV number, must be 3 digits.");
            cvvAlert.showAndWait();
        } 
        else{
            inputValid = true;
        }
        return inputValid;
    }
//---------------------------------------------------------------------------------------------------------------
    /**
     * This method examines the validity of cvv, zipcode, and cardnumber input. 
     * If theyre all good, payment is processed. This is strictly a simulation payment so even
     * with all conditions met, it is only approved 90% of the time.
     * @param event
     */
    @FXML
    private void handlePayNow(ActionEvent event) {
    boolean cvvValid = cvvLength();
    boolean zipValid = zipCodeLength();
    boolean cardValid = cardNumberLength();

    if (cvvValid && zipValid && cardValid) {
        boolean success = rand.nextDouble() < 0.9; 

        Alert paymentStatusAlert;
        if (success) {
            paymentStatusAlert = new Alert(Alert.AlertType.INFORMATION);
            paymentStatusAlert.setTitle("Payment Successful");
            paymentStatusAlert.setHeaderText(null);
            paymentStatusAlert.setContentText("Your payment was successful! 🎉");
        } else {
            paymentStatusAlert = new Alert(Alert.AlertType.ERROR);
            paymentStatusAlert.setTitle("Payment Failed");
            paymentStatusAlert.setHeaderText(null);
            paymentStatusAlert.setContentText("Your payment failed. Please try again.");
        }
        paymentStatusAlert.showAndWait();
     }
    }
}