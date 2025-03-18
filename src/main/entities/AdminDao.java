package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDao {
    private static final Map<String, Admin> admins = DatabaseClass.getAdmin();

    public void addAdmin(String adminName, int assets) {
        admins.put(adminName, new Admin(adminName, assets));
    }

    public Admin getAdmin(String adminName) {
        return admins.get(adminName);
    }

    public int updateAdminAssets(String adminName, int totalPrice) {
        Admin admin = getAdmin(adminName);
        int currentAssets = admin.getAssets() + totalPrice;

        Admin updatedAdmin = new Admin(admin.getName(), currentAssets);
        admins.put(adminName, updatedAdmin);

        return currentAssets;

    }
}

