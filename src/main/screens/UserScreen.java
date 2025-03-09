package main.screens;

import main.Input;
import main.entities.UserDao;

public class UserScreen implements AccountScreen {
    Input input = new Input();
    UserDao user = new UserDao();

    private static String loggedInUser;

    @Override
    public void showCreateScreen() {
        System.out.println("회원의 정보를 입력해주세요 ex) 1, 30000");
        String info = input.getInput();
        // TODO: 동일한 아이디의 회원이 있는 경우 "이미 생성된 회원 입니다" 출력
        create(info);
        System.out.println(user.getAllUsers());
    }

    @Override
    public void showLoginScreen() {
        System.out.println("접속할 회원의 번호를 입력해주세요");
        String userId = input.getInput();

        if (validate(userId)) {
            login(userId);
            loggedInUser = userId;
        }
    }

    @Override
    public void create(String s) {
        String[] parts = input.splitAccountInput(s);

        String id = parts[0];
        int assets = Integer.parseInt(parts[1]);

        user.addUser(id, assets);
    }

    @Override
    public void login(String s) {
        user.getUser(s);
    }

    @Override
    public boolean validate(String s) {
        if (user.getUser(s) == null) {
            System.out.println("해당 회원은 존재하지 않습니다.");
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
