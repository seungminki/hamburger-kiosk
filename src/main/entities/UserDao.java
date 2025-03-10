package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    private Map<String, User> users = DatabaseClass.getUser();

    public void addUser(String userId, int assets) {
        users.put(userId, new User(userId, assets));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public int updateUserAssets(String userId, int totalPrice) {
        User user = getUser(userId);
        int currentAssets = user.getAssets() - totalPrice;

        User updatedUser = new User(user.getId(), currentAssets);
        users.put(userId, updatedUser);

        return currentAssets;

    }
}