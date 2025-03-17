package main.entities;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
    private static final Map<String, Product> products = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();
    private static final Map<String, Admin> admins = new HashMap<>();

    public static Map<String, Product> getProduct() {
        return products;
    }

    public static Map<String, User> getUser() {
        return users;
    }

    public static Map<String, Admin> getAdmin() {
        return admins;
    }


}
