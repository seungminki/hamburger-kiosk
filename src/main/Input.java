package main;

import main.enums.InputSign;

import java.util.Scanner;

public class Input {
    Scanner sc = new Scanner(System.in);

    public int getMenuInput() {
        return sc.nextInt();
    }

    public String getInput() {
        return sc.nextLine().trim();
    }

    public String[] splitAccountInput(String input) {
        return input.replace(InputSign.ACCOUNT_SEPARATOR.getSign(), InputSign.EMPTY.getSign()).split(InputSign.DELIMITER.getSign());
    }

    public String[] splitProductInput(String input) {
        return input.replaceAll(InputSign.PRODUCT_REGEX.getSign(), InputSign.EMPTY.getSign()).split(InputSign.DELIMITER.getSign());
    }

}
