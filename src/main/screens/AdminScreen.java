package main.screens;

import main.Input;
import main.entities.AdminDao;

public class AdminScreen implements AccountScreen {
    Input input = new Input();
    AdminDao admin = new AdminDao();

    private static String loggedInAdmin;

    @Override
    public void showCreateScreen() {
        System.out.println("관리자의 정보를 입력해주세요 ex) 관리자1, 100000");
        String adminInfo = input.getInput();
        // TODO: 동일한 이름의 관리자가 있는 경우 "이미 생성된 관리자 입니다" 출력
        create(adminInfo);
        System.out.println(admin.getAllAdmins());
    }

    @Override
    public void showLoginScreen() {
        System.out.println("접속할 관리자의 이름을 입력하세요.");
        String adminName = input.getInput();

        if (validate(adminName)) {
            login(adminName);
            loggedInAdmin = adminName;
        }
    }

    @Override
    public void create(String adminInfo) {
        String[] parts = input.splitAccountInput(adminInfo);

        String id = parts[0];
        int assets = Integer.parseInt(parts[1]);

        admin.addAdmin(id, assets);
    }

    @Override
    public void login(String adminName) {
        admin.getAdmin(adminName);
    }

    @Override
    public boolean validate(String adminName) {
        if (admin.getAdmin(adminName) == null) {
            System.out.println("해당 관리자는 존재하지 않습니다.\n");
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