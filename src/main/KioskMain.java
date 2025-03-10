package main;

import main.screens.AdminScreen;
import main.screens.ProductScreen;
import main.screens.RootScreen;
import main.screens.UserScreen;

import java.io.IOException;

public class KioskMain {
    public static void main(String[] args) throws IOException {
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





