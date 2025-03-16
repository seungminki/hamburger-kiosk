package main.entities;

public class Admin {
    private final String name;
    private final int assets;

    public Admin(String name, int assets) {
        this.name = name;
        this.assets = assets;
    }

    public String getName() {
        return name;
    }

    public int getAssets() {
        return assets;
    }
}
