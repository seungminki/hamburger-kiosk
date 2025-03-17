package main;

import main.entities.ProductDao;
import main.entities.UserDao;

import main.enums.*;

import java.util.ArrayList;
import java.util.List;

public class OrderProduct {
    ProductDao product = new ProductDao();
    UserDao user = new UserDao();

    private static final int NAME = 0;
    private static final int PRICE = 1;

    public void processOrder(String userId, String[] items) {
        for (List<String> item : parseOrderItems(userId, items)) {
            processSingleItem(item);
        }
    }

    private List<List<String>> parseOrderItems(String userId, String[] items) {
        List<List<String>> parsedItems = new ArrayList<>();
        int totalPrice = 0;

        for (String item : items) {
            String[] parts = item.split(InputSign.PRODUCT_SEPARATOR.getSign());
            List<String> itemList = new ArrayList<>();

            validateProductName(parts[NAME]);

            itemList.add(parts[NAME]);
            itemList.add(parts[PRICE]);
            parsedItems.add(itemList);

            int eachPrice = product.getProduct(parts[NAME]).getPrice() * Integer.parseInt(parts[PRICE]);
            totalPrice += eachPrice;
        }

        validateUserAssets(userId, totalPrice);

        return parsedItems;
    }

    private void validateProductName(String productName) {
        if (product.getProduct(productName) == null) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRODUCT.getMessage());
        }
    }

    private void validateUserAssets(String userId, int totalPrice) {
        if (user.getUser(userId).getAssets() < totalPrice) {
            throw new IllegalArgumentException(ErrorMessage.OVER_USER_ASSETS.getMessage());
        }
    }

    private void processSingleItem(List<String> item) {
        String name = item.get(NAME);
        int count = Integer.parseInt(item.get(PRICE));

        String pCategory = product.getProduct(name).getCategory();

        if (pCategory.equals(ProductType.SIDE.getType())) {
            buySide(name, count);
        }

        if (pCategory.equals(ProductType.DRINK.getType())) {
            buyDrink(name, count);
        }

        if (pCategory.equals(ProductType.HAMBURGER.getType())) {
            buyHamburger(name, count);
        }

        if (pCategory.equals(ProductType.SET.getType())) {
            buySet(name, count);
        }
    }

    private void validateProductQuantity(String productName, int buyQuantity) {
        try {
            int currentQuantity = Integer.parseInt(product.getProduct(productName).getQuantity());
            if (currentQuantity < buyQuantity) {
                throw new IllegalArgumentException(ErrorMessage.OVER_PRODUCT_QUANTITY.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.SOLD_OUT_PRODUCT.getMessage());
        }
    }

    private void buySide(String pName, int quantity) {
        validateProductQuantity(pName, quantity);
        product.updateProductQuantity(pName, quantity);
    }

    private void buyDrink(String pName, int quantity) {
        validateProductQuantity(pName, quantity);
        product.updateProductQuantity(pName, quantity);
    }

    private void buyHamburger(String pName, int quantity) {
        validateProductQuantity(pName, quantity);

        product.updateProductQuantity(pName, quantity);
        product.updateProductQuantity(pName + ProductType.SET.getType(), quantity);

    }

    private void buySet(String pName, int quantity) {
        validateProductQuantity(pName, quantity);

        try {
            buySide(ProductType.FRENCH_FRIES.getType(), quantity);
            buyDrink(ProductType.COKE.getType(), quantity);

        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.OVER_HAMBURGER_SET_QUANTITY.getMessage());
        }

        buyHamburger(pName.replace(ProductType.SET.getType(), ""), quantity);
    }
}
