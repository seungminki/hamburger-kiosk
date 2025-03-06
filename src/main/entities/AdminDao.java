package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDao {
    private Map<String, Admin> admins = DatabaseClass.getAdmin();

    public void addAdmin(String adminName, int assets) {
        admins.put(adminName, new Admin(adminName, assets));
    }

    public List<Admin> getAllAdmins() {
        return new ArrayList<>(admins.values());
    }

    public Admin getAdmin(String adminName) {
        return admins.get(adminName);
    }
}

