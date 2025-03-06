package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    private Map<String, User> users = DatabaseClass.getUser();

    public void addUser(String userid, int assets) {
        users.put(userid, new User(userid, assets));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(String userid) {
        return users.get(userid);
    }
}