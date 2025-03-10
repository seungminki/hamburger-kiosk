package main;

import java.util.Scanner;

public class Input {
    Scanner sc = new Scanner(System.in);

    public int getMenuInput() {
        int input = sc.nextInt();

        return input;
    }

    public String getInput() {
        String input = sc.nextLine().trim();

        return input;
    }

    public void close() {
        sc.close();
    }


    public String[] splitAccountInput(String input) {
        String[] parts = input.replace(" ", "").split(",");
        // TODO: Account + Assets 형태 아닐 시 에러
        
        return parts;
    }

    public String[] splitProductInput(String input) {
        String[] items = input.replaceAll("[\\[\\]]", "").split(",");

        return items;
    }

}
