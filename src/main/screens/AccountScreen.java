package main.screens;

public interface AccountScreen {
    void showCreateScreen();

    void showLoginScreen();

    void create(String accountInfo);

    void login(String accountName);

    boolean validate(String accountName);

    boolean isLoggedIn();
}
