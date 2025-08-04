package ecommerce.com;

import ecommerce.com.cart.ProductCatalog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static Scene scene;
    private static ProductCatalog catalog;

    @Override
    public void start(Stage stage) throws IOException {
        // 1️⃣ Initialize and load the shared product catalog
        catalog = new ProductCatalog();
        catalog.loadFromFile("products.txt");

        // 2️⃣ Set up the primary window using the file-system loader
        Parent mainRoot = loadViewWithCatalog("mainPage", catalog);
        scene = new Scene(mainRoot, 640, 480);
        stage.setScene(scene);
        stage.show();

        // 3️⃣ Open a secondary window the same way
        Parent secondaryRoot = loadViewWithCatalog("primary", catalog);
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(secondaryRoot, 640, 480));
        secondStage.show();
    }

    /**
     * Replace the current scene root with the given FXML view, re-injecting the catalog.
     */
    public static void setRoot(String fxmlName) throws IOException {
        Parent newRoot = loadViewWithCatalog(fxmlName, catalog);
        scene.setRoot(newRoot);
    }

    /**
     * Loads an FXML view from the file system under src/main/resources/ecommerce.com/, injects the catalog,
     * and returns the root node.
     */
    private static Parent loadViewWithCatalog(String fxmlName, ProductCatalog catalog) throws IOException {
        File fxmlFile = new File(
                "src/main/resources/ecommerce.com/" + fxmlName + ".fxml"
        );
        if (!fxmlFile.exists()) {
            throw new IOException("Cannot find FXML file: " + fxmlFile.getAbsolutePath());
        }
        URL url = fxmlFile.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Object ctrl = loader.getController();
        if (ctrl instanceof PrimaryController) {
            ((PrimaryController) ctrl).setCatalog(catalog);
        } else if (ctrl instanceof SecondaryController) {
            ((SecondaryController) ctrl).setCatalog(catalog);
        }
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
