package main;

import main.entities.Product;
import main.enums.InputSign;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExtractMd {
    private static final String PRODUCT_MD_PATH = "src/main/resources/products.md";

    private static final int NAME = 0;
    private static final int PRICE = 1;
    private static final int QUANTITY = 2;
    private static final int DESCRIPTION = 3;
    private static final int CATEGORY = 4;

    public List<String> readMdFiles() throws IOException {
        return Files.readAllLines(Paths.get(PRODUCT_MD_PATH));
    }

    public List<Product> alterDataLines(List<String> lines) {
        int headerLines = 1;

        List<Product> productList = new ArrayList<>();

        lines.stream().skip(headerLines).forEach(line -> {
            String[] parts = line.split(InputSign.DELIMITER.getSign());

            productList.add(new Product(
                            parts[NAME],
                            Integer.parseInt(parts[PRICE]),
                            parts[QUANTITY],
                            parts[DESCRIPTION].replace(InputSign.QUOTATION.getSign(), InputSign.EMPTY.getSign()),
                            parts[CATEGORY]
                    )
            );
        });

        return productList;

    }
}
