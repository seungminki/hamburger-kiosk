package main.screens;

import main.Input;
import main.OrderProduct;
import main.entities.*;

import java.io.IOException;

public class ProductScreen {
    Input input = new Input();

    ProductDao product = new ProductDao();
    AdminDao admin = new AdminDao();
    UserDao user = new UserDao();

    OrderProduct order = new OrderProduct();

    public void welcomeScreen() throws IOException {

        product.addProduct();
        while (true) {
            System.out.println("=================================");

            String userId = UserScreen.connectedAccount();
            String adminName = AdminScreen.connectedAccount();
            showAccount(userId, adminName);

            showAllProduct();
            buyProduct();

            if (!ynRebuyProduct()) {
                break;
            }

        }

    }

    private void showAccount(String userId, String adminName) {
        System.out.println("안녕하세요. " + userId + "님 햄버거 가게 입니다.");
        System.out.println("현재 접속된 관리자는 " + adminName + "입니다.");
        System.out.println("\n");
    }

    private void showAllProduct() {
        System.out.println("=햄버거\n");
        product.getProductByCategory("햄버거");

        System.out.println("=세트\n");
        product.getProductByCategory("세트");

        System.out.println("=사이드\n");
        product.getProductByCategory("사이드");

        System.out.println("=음료수\n");
        product.getProductByCategory("음료수");

        System.out.println("\n");
    }

    private String[] inputBuyProduct() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [치킨버거-3],[불고기버거세트-2])");
        String productList = input.getInput();

        return input.splitProductInput(productList);
    }

    private void buyProduct() {
        try {
            String[] items = inputBuyProduct();

            for (String item : items) {
                order.processOrderItems(item);
            }
            resultReceipt(items);
        } catch (Exception e) {
            System.out.println("[ERROR] 주문 처리 중 오류가 발생했습니다.\n");
        }
    }

    private void resultReceipt(String[] items) {
        int totalNum = 0;
        int totalPrice = 0;

        System.out.println("=====================");
        System.out.println("상품명 수량 금액");

        for (String item : items) {
            String[] parts = item.split("-");

            String name = parts[0];
            int count = Integer.parseInt(parts[1]);

            Product p = product.getProduct(name);

            int EachPrice = p.getPrice() * count;

            System.out.println(name + " " + count + " " + EachPrice);

            totalNum += count;
            totalPrice += EachPrice;
        }

        System.out.println("=====================");
        System.out.println("총구매액 " + totalNum + " " + totalPrice);
        System.out.println("=====================");

        resultAccountAssets(totalPrice);
    }

    private void resultAccountAssets(int totalPrice) {
        String adminName = AdminScreen.connectedAccount();
        String userId = UserScreen.connectedAccount();

        int totalAdminAssets = admin.updateAdminAssets(adminName, totalPrice);
        int totalUserAssets = user.updateUserAssets(userId, totalPrice);

        // TODO: 회원의 보유 금액이 -가 된다면 에러

        System.out.println("판매자: " + adminName + ", " + totalAdminAssets);
        System.out.println("구매자: " + userId + ", " + totalUserAssets);
    }

    public boolean ynRebuyProduct() {

        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");

        String yn = input.getInput().trim();

        if (yn.equals("Y") || yn.equals("y")) {
            return true;
        }

        if (yn.equals("N") || yn.equals("n")) {
            return false;
        }

        // TODO: y,n 말고 다른 문자 입력시 에러 처리

        return false;


    }
}
