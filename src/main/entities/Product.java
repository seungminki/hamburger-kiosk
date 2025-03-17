package main.entities;

public class Product {
    private final String name;
    private final int price;
    private final String quantity;
    private final String description;
    private final String category;

    public Product(String name, int price, String quantity, String description, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }

    public Product(Product product) {
        this.name = product.name;
        this.price = product.price;
        this.quantity = product.quantity;
        this.description = product.description;
        this.category = product.category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getProductInfo() {
        return String.format("-%s, %d원, %s개, %s", name, price, quantity, description);
    }


}

