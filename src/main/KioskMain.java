package main;

import main.entities.ProductDao;
import main.screens.RootScreen;

import java.io.IOException;

public class KioskMain {
    public static void main(String[] args) throws IOException {
        RootScreen rs = new RootScreen();
        ProductDao.addProduct();

        int exitNum = 0;

        while (true) {
            int inputValue = rs.welcomeScreen();

            if (inputValue == exitNum) {
                break;
            }

            rs.moveScreen(inputValue);
        }
    }
}





