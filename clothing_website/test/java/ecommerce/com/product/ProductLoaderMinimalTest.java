package ecommerce.com.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductLoaderMinimalTest {
    @TempDir
    Path tempDir;

    @Test
    void readFile_createsProductsFromSingleLine() throws Exception {
        // Arrange: one line with 6 comma-separated fields
       // String line = "Test Shirt, 15.00, M, Tops, ID001, /img/shirt.png";
        String line = "Test Shirt, 15.00, ID001, /img/shirt.png";
        Files.writeString(tempDir.resolve("products.txt"), line);

        System.setProperty("user.dir", tempDir.toString());

        // Act
        List<Product> products = ProductLoader.readFile();

        // Assert: expect 6 products (since loader treats each field as separate Product)
        assertEquals(6, products.size());

        // Optional: check the first and last entries
        Product first = products.get(0);
        Product last = products.get(products.size() - 1);

        assertNotNull(first);
        assertNotNull(last);
    }
}
