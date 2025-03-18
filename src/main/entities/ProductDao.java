package main.entities;

import main.ExtractMd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDao {
    private static final Map<String, Product> products = DatabaseClass.getProduct();

    public static void addProduct() throws IOException {
        ExtractMd extractMd = new ExtractMd();

        List<String> lines = extractMd.readMdFiles();
        List<Product> productList = extractMd.alterDataLines(lines);

        productList.forEach(product -> products.put(product.getName(), new Product(product)));

    }

    public Product getProduct(String productName) {
        return products.get(productName);
    }


    public void updateProductQuantity(String productName, int productCount) {
        Product product = getProduct(productName);

        int currentQuantity = Integer.parseInt(product.getQuantity());

        Product updatedProduct = new Product(
                product.getName(),
                product.getPrice(),
                String.valueOf(currentQuantity - productCount),
                product.getDescription(),
                product.getCategory()
        );

        products.put(productName, updatedProduct);

    }


    public void getProductByCategory(String category) {

        List<Product> productList = new ArrayList<>();

        for (Product p : products.values()) {
            if (p.getCategory().equals(category)) {
                productList.add(p);
            }
        }

        System.out.println("=" + category);
        for (Product p : productList) {
            System.out.println(p.getProductInfo());
        }
        System.out.println("\n");


    }
}
