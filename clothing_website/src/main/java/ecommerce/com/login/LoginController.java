package ecommerce.com.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

/**
 * this class handles the logic for the login page
 */
public class LoginController {

    @FXML private Button loginButton;
    @FXML private TextField userNameTextField;
    @FXML private TextField passWordTextField;
    @FXML private Label loginPageLabel;

    private static String PreviousPage;

    /**
     * handles the action of clicking on the login button in the login page
     * this method gets the inputted username and password by user, validates them and
     * checks them with the existing user information
     * @param event
     */
    @FXML
    private void clickOnLoginButton(ActionEvent event) {
        String userName = userNameTextField.getText();
        String passWord = passWordTextField.getText();
        File f = new File("usersInfo.txt");

        if(!LoginUtils.isUserNameValid(userName)){
            loginPageLabel.setText("The username must have at least 1 letter");
            return;
        }

        if(!LoginUtils.validatePassword(passWord)){
            loginPageLabel.setText("Password must be at least 6 character long");
            return;
        }

        if(LoginUtils.doesUserNameExist(userName, f)){
            if(LoginUtils.doPasswordsMatch(userName, passWord, f)){
                loginPageLabel.setText("welcome back!");
                openPreviousPage();
            } else{
                loginPageLabel.setText("incorrect password");
            }
        } else {
            LoginUtils.saveLoginInfo(userName, passWord);
            loginPageLabel.setText("new user registered");
            openPreviousPage();
        }
    }

    /**
     * this method takes the user back to the main page after a successful login
     */
    private void openPreviousPage(){ //after thr user clicks on the login button and it is successful this takes them to the previous page
        FXMLLoader loader = new FXMLLoader();
        try{
            if("mainPage".equals(PreviousPage)){ // if the previous page was main page go back to main page after login
                loader = new FXMLLoader(getClass().getResource("/ecommerce/com/mainPage.fxml"));
            } else { // for safety in case the page file is not correct
                loader = new FXMLLoader(getClass().getResource("/ecommerce/com/mainPage.fxml"));
            }
            Parent root = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * this method takes the name of the previous page as a string and stores it to use
     * in the openPreviousPage method
     * @param pageName
     */
    public void PreviousPageName(String pageName){
        PreviousPage = pageName; //name of the previous page that we go to the login page from.
    }
}
