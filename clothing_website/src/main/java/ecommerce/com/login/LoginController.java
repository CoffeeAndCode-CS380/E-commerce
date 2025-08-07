package ecommerce.com.login;

import ecommerce.com.sizeSelectionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LoginController {

    @FXML private Button loginButton;
    @FXML private TextField userNameTextField;
    @FXML private TextField passWordTextField;
    @FXML private Label loginPageLabel;

    private static String PreviousPage;

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
                FXMLLoader loader = new FXMLLoader();
                try{
                    if("mainPage".equals(PreviousPage)){ // if the previous page was main page go back to main page after login
                        loader = new FXMLLoader(getClass().getResource("/ecommerce/com/mainPage.fxml"));
                    } else if ("sizeSelection".equals(PreviousPage)) {
                        loader = new FXMLLoader(getClass().getResource("/ecommerce/com/sizeSelection.fxml"));
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
            } else{
                loginPageLabel.setText("incorrect password");
            }
        } else {
            loginPageLabel.setText("username not found!");
        }
    }

    public void backToPreviousPage(String pageName){
        PreviousPage = pageName; //name of the previous page that we go to the login page from.
    }

}
