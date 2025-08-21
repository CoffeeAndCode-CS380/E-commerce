package ecommerce.com.product;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ProductLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void fileNotFound_throwsIOException() {
        assertThrows(IOException.class, () -> ProductLoader.readFile("no_such.txt"));
    }
    @Test
    void readsValidTxtFile() throws IOException {
        Path f = tempDir.resolve("products.txt");
        Files.writeString(f,
                "T-Shirt,19.99,M,Clothing,TSH001,images/t.jpg\n" +
                        "Jeans,49.99,L,Clothing,JNS002,images/j.jpg\n");
        List<Product> items = ProductLoader.readFile(f.toString());
        assertEquals(2, items.size());
        assertEquals("T-Shirt", items.get(0).getProductName());
    }
    @Test
    void emptyFile_returnsEmptyList() throws IOException {
        Path f = tempDir.resolve("empty.txt");
        Files.writeString(f, "");
        List<Product> items = ProductLoader.readFile(f.toString());
        assertTrue(items.isEmpty());
    }

    @Test

    void missingCategories_isSkipped() throws IOException {
        Path f = tempDir.resolve("bad.txt");
        Files.writeString(f,
                "BadLineWithTooFewFields\n" +                        // malformed
                        "Watch,129.99,M,Accessories,WTCH009,images/w.jpg\n"  // valid
        );

        List<Product> items = ProductLoader.readFile(f.toString());

        // should only load the valid product
        assertEquals(1, items.size());
        assertEquals("Watch", items.get(0).getProductName());
    }
    @Test
    void missingCategories_badPrice_throws() throws IOException {
        Path f = tempDir.resolve("badprice.txt");
        Files.writeString(f, "T-Shirt,XX.XX,M,Clothing,TSH001,images/t.jpg\n");
        assertThrows(NumberFormatException.class, () -> ProductLoader.readFile(f.toString()));
    }


}
