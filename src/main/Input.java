package main;

import java.util.Scanner;

public class Input {
    Scanner sc = new Scanner(System.in);

    private static final String ACCOUNT_INPUT_DELIMITER = " ";
    private static final String PRODUCT_INPUT_REGEX = "[\\[\\]]";
    private static final String EMPTY_STRING = "";
    private static final String INPUT_DELIMITER = ",";

    public int getMenuInput() {
        return sc.nextInt();
    }

    public String getInput() {
        return sc.nextLine().trim();
    }

    public String[] splitAccountInput(String input) {
        return input.replace(ACCOUNT_INPUT_DELIMITER, EMPTY_STRING).split(INPUT_DELIMITER);
    }

    public String[] splitProductInput(String input) {
        return input.replaceAll(PRODUCT_INPUT_REGEX, EMPTY_STRING).split(INPUT_DELIMITER);
    }

}
