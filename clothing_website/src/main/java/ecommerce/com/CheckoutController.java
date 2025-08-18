package ecommerce.com;
import java.util.Properties;
import java.util.Random;

import ecommerce.com.cart.CartUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.scene.Node;

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
    @FXML private TextField emailTextField;
    private final Random rand = new Random();

    private static final String EMAIL_FROM = "rdelgado5050@gmail.com"; //DO NOT CHANGE THIS
	//private static final String EMAIL_TO = "rdelgado134@gmail.com";
	private static final String APP_PASSWORD = "seps bcuv sdmp okmy"; //DO NOT CHANGE THIS

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
            try {
                sendEmail();
                CartUtils.clearCart();
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
                  Parent mainPage = loader.load();
                  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                  stage.setScene(new Scene(mainPage));
                  stage.show(); 

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            paymentStatusAlert = new Alert(Alert.AlertType.ERROR);
            paymentStatusAlert.setTitle("Payment Failed");
            paymentStatusAlert.setHeaderText(null);
            paymentStatusAlert.setContentText("Your payment failed. Please try again.");
        }
        paymentStatusAlert.showAndWait();
     }
    }
//---------------------------------------------------------------------------------------------------------------
    @FXML
    public String getEmail(){
        String result = emailTextField.getText();
        System.out.println(result);
        return result;
    }

    	
    public void sendEmail() throws Exception {
        //String EMAIL_TO = getEmail();
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(EMAIL_FROM));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTextField.getText()));
		message.setSubject("CoffeeAndCode :: Purchase Confirmation");
		message.setText("Thank you for your ordering from CoffeeAndCode, go Matadors! \n\n --sent from Ecommerce project");
		Transport.send(message);
	}
	
	private static Session getEmailSession() {
		return Session.getInstance(getGmailProperties(), new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
		    }
		});
	}
	
	private static Properties getGmailProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return prop;
	}    
}