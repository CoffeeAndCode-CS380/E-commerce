package ecommerce.com;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CheckoutController {

    @FXML private ChoiceBox<String> stateChoiceBox;
    @FXML private TextField addressTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField cardNumTextField;
    @FXML private TextField nameOnCardTextField;
    @FXML private TextField billingZipTextField;
    @FXML private TextField cvvTextField;

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

    @FXML
    private void zipCodeLength(){
        String zipCode = zipCodeTextField.getText();
        if(zipCode.matches("\\d{5}") ){ //force user to have a 5 digit zipcode
            System.out.println("Valid Zipcode");
        } else {
            System.out.println("Invalid Zipcode");
        }
    }

    @FXML
    private void cardNumberLength(){
        String cardNumber = cardNumTextField.getText();
        if(cardNumber.matches("\\d{16}") ){ //force user to have a 16 digit card number
            System.out.println("Valid card number");
        } else {
            System.out.println("Invalid card number");
        }
    }

    @FXML
    private void cvvLength(){
        String cvv = cvvTextField.getText();
        if(cvv.matches("\\d{3}") ){ //force user to have a 3 digit cvv
            System.out.println("Valid cvv");
        } else {
            System.out.println("Invalid cvv");
        }
    }



}