package com.hirbod.ecommerceproject;

import com.hirbod.ecommerceproject.UserUtilityFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable{

    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView lockImageView;
    //add more in case of more pictures. also change the name as needed
    @FXML
    private TextField enterUsernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){ // need this show the pictures and logos on the window

        // resources -> right click -> mark directory as -> resource root. I did this and then copied
        // path from content root when I right clicked on the image. get to path by right clicking on the image
        File lockFile = new File("src/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        //copy this exat 3 lines of code if you add more pics.
    }

    public void loginButtonOnAction (ActionEvent event){
        //run this if neither of username and password is blank
        if (enterUsernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            //store them in varibale to use later in the finction calls
            String username = enterUsernameTextField.getText();
            String password = enterPasswordField.getText();
            //made saveLoginInfo tpe bool to keep these cleaner
            boolean successfulLogin = UserUtilityFile.saveLoginInfo(username, password);
            if(successfulLogin){
                loginMessageLabel.setText("Logged in successfully!");
            } else {
                loginMessageLabel.setText("Username already exists!");
            }
        } else {
            loginMessageLabel.setText("Please fill in all the fields"); //to keep the error message and label's text dynamic
        }
    }
}