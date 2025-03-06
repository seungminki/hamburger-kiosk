package main.entities;

public class Product {
    private String name;
    private int price;
    private String quantity;
    private String description;
    private String category;

    public Product(String name, int price, String quantity, String description, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
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

    @Override
    public String toString() {
        return "-%s, %d원, %s개, %s".formatted(name, price, quantity, description);
    }


}

