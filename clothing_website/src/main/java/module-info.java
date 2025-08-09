module ecommerce.com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ecommerce.com to javafx.fxml;
    exports ecommerce.com;
    exports ecommerce.com.login to javafx.fxml; //makes the public types to be accessible in other modules
    opens ecommerce.com.login to javafx.fxml; //allow javafx to access everything in the login package
    exports ecommerce.com.search to javafx.fxml;
    opens  ecommerce.com.search to javafx.fxml;
}
