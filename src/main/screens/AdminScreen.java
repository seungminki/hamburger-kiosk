package main.screens;

import main.Input;
import main.entities.AdminDao;
import main.enums.ErrorMessage;

public class AdminScreen implements AccountScreen {
    Input input = new Input();
    AdminDao admin = new AdminDao();

    private static String loggedInAdmin;

    private static final int PARTS_NAME = 0;
    private static final int PARTS_ASSETS = 1;

    @Override
    public void showCreateScreen() {
        System.out.println("관리자의 정보를 입력해주세요 ex) 관리자1, 100000");
        create(input.getInput());
    }

    @Override
    public void showLoginScreen() {
        System.out.println("접속할 관리자의 이름을 입력하세요.");
        login(input.getInput());
    }

    @Override
    public void create(String adminInfo) {
        String[] parts = input.splitAccountInput(adminInfo);

        if (validate(parts[PARTS_NAME])) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_ADMIN.getMessage());
        }

        admin.addAdmin(parts[PARTS_NAME], Integer.parseInt(parts[PARTS_ASSETS]));
    }

    @Override
    public void login(String adminName) {
        if (!validate(adminName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ADMIN.getMessage());
        }
        loggedInAdmin = adminName;
    }

    @Override
    public boolean validate(String adminName) {
        if (admin.getAdmin(adminName) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLoggedIn() {
        return (loggedInAdmin != null);
    }

    public static String connectedAccount() {
        return loggedInAdmin;
    }

}