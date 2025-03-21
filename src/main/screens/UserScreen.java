package main.screens;

import main.Input;
import main.entities.UserDao;
import main.enums.ErrorMessage;

public class UserScreen implements AccountScreen {
    Input input = new Input();
    UserDao user = new UserDao();

    private static String loggedInUser;

    private static final int PARTS_ID = 0;
    private static final int PARTS_ASSETS = 1;

    @Override
    public void showCreateScreen() {
        System.out.println("회원의 정보를 입력해주세요 ex) 1, 30000");
        create(input.getInput());
    }

    @Override
    public void showLoginScreen() {
        System.out.println("접속할 회원의 번호를 입력해주세요");
        login(input.getInput());
    }

    @Override
    public void create(String userInfo) {
        String[] parts = input.splitAccountInput(userInfo);

        if (validate(parts[PARTS_ID])) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_USER.getMessage());
        }
        user.addUser(parts[PARTS_ID], Integer.parseInt(parts[PARTS_ASSETS]));
    }

    @Override
    public void login(String userId) {
        if (!validate(userId)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_USER.getMessage());
        }
        loggedInUser = userId;
    }

    @Override
    public boolean validate(String userId) {
        if (user.getUser(userId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLoggedIn() {
        return (loggedInUser != null);
    }

    public static String connectedAccount() {
        return loggedInUser;
    }

}
