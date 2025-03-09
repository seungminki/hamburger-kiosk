package main;

import main.entities.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExtractMd {
    String productMdPath = "src/main/resources/products.md";

    public List<String> readMdFiles() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(productMdPath));

        return lines;
    }

    public List<Product> alterDataLines(List<String> lines) {
        List<Product> productList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");

            productList.add(new Product(values[0], Integer.parseInt(values[1]), values[2],
                    values[3].replaceAll("\"", ""), values[4]));
        }

        return productList;

    }

}
