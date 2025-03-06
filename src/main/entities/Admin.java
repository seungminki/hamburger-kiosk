package main.entities;

public class Admin {
    private String name;
    private int assets;

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

    @Override
    public String toString() {
        return "Admin{name='" + name + "', assets=" + assets + "}";
    }
}
