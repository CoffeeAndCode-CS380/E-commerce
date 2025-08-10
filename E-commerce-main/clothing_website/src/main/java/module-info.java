module ecommerce.com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ecommerce.com to javafx.fxml;
opens ecommerce.com.login to javafx.fxml;

    exports ecommerce.com;
}
