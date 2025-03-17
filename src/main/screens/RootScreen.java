package main.screens;

import main.Input;
import main.enums.ErrorMessage;

public class RootScreen {
    Input input = new Input();

    AdminScreen as = new AdminScreen();
    UserScreen us = new UserScreen();
    ProductScreen ps = new ProductScreen();

    private static final int CREATE_ADMIN = 1;
    private static final int LOGIN_ADMIN = 2;
    private static final int CREATE_USER = 3;
    private static final int LOGIN_USER = 4;

    public int welcomeScreen() {
        System.out.println("\n");
        System.out.println("0. 종료");
        System.out.println("1. 관리자 생성");
        System.out.println("2. 관리자 접속");
        System.out.println("3. 회원 생성");
        System.out.println("4. 회원 접속");
        System.out.println("\n");

        return input.getMenuInput();
    }

    public void moveScreen(int inputValue) {

        if (inputValue == CREATE_ADMIN) {
            as.showCreateScreen();
            return;
        }

        if (inputValue == LOGIN_ADMIN) {
            as.showLoginScreen();
            return;
        }

        if (inputValue == CREATE_USER) {
            us.showCreateScreen();
            return;
        }

        if (inputValue == LOGIN_USER) {
            us.showLoginScreen();
            validateLogin();
            ps.welcomeScreen();
            return;
        }

        throw new IllegalArgumentException(ErrorMessage.INVALID_ROOT_MENU_NUM.getMessage());
    }

    private void validateLogin() {
        if (!us.isLoggedIn()) {
            throw new IllegalArgumentException(ErrorMessage.UNLOGGED_STATUS_USER.getMessage());
        }
        if (!as.isLoggedIn()) {
            throw new IllegalArgumentException(ErrorMessage.UNLOGGED_STATUS_ADMIN.getMessage());
        }
    }

}

