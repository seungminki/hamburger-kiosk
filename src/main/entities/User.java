package main.entities;

public class User {
    private String id;
    private int assets;

    public User(String id, int assets) {
        this.id = id;
        this.assets = assets;
    }

    public String getId() {
        return id;
    }

    public int getAssets() {
        return assets;
    }
}

