package main.screens;

import main.Input;

import java.io.IOException;

public class RootScreen {
    Input input = new Input();

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

    public void moveScreen() throws IOException {
        // TODO: Complexity 해결
        RootScreen rs = new RootScreen();
        AdminScreen as = new AdminScreen();
        UserScreen us = new UserScreen();
        ProductScreen ps = new ProductScreen();

        boolean status = true;

        int exitNum = 0;
        int createAdminNum = 1;
        int loginAdminNum = 2;
        int createUserNum = 3;
        int loginUserNum = 4;


        while (status) {
            int inputValue = rs.welcomeScreen();

            if (inputValue == exitNum) {
                status = false;
            }

            if (inputValue == createAdminNum) {
                as.showCreateScreen();
            }

            if (inputValue == loginAdminNum) {
                as.showLoginScreen();
            }

            if (inputValue == createUserNum) {
                us.showCreateScreen();
            }

            if (inputValue == loginUserNum) {
                us.showLoginScreen();
                if (us.isLoggedIn()) {
                    if (!as.isLoggedIn()) {
                        System.out.println("관리자 접속 후 이용해주세요.");
                    } else {
                        ps.welcomeScreen();
                    }
                }
            }

            if (inputValue != exitNum && inputValue != createAdminNum &&
                    inputValue != loginAdminNum && inputValue != createUserNum &&
                    inputValue != loginUserNum) {
                System.out.println("유효한 메뉴 번호를 입력하세요.");
            }

        }

    }
}
