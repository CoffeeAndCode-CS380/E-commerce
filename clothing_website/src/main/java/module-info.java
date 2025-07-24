module ecommerce.com {
    requires javafx.controls;
    requires javafx.fxml;

    opens ecommerce.com to javafx.fxml;
    exports ecommerce.com;
}
