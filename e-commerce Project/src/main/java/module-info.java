module com.hirbod.ecommerceproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.hirbod.ecommerceproject to javafx.fxml;
    exports com.hirbod.ecommerceproject.login;
    opens com.hirbod.ecommerceproject.login to javafx.fxml;
}