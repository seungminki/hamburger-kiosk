package main.screens;

import main.Input;
import main.OrderProduct;
import main.entities.*;
import main.enums.*;

public class ProductScreen {
    Input input = new Input();

    ProductDao product = new ProductDao();
    AdminDao admin = new AdminDao();
    UserDao user = new UserDao();

    OrderProduct order = new OrderProduct();

    public void welcomeScreen() {
        showAccount();

        do {
            showAllProduct();
            buyProduct();

        } while (ynRebuyProduct());
    }


    private void showAccount() {
        System.out.println("=================================");
        System.out.println("안녕하세요. " + UserScreen.connectedAccount() + "님 햄버거 가게 입니다.");
        System.out.println("현재 접속된 관리자는 " + AdminScreen.connectedAccount() + "입니다.\n");
    }

    private void showAllProduct() {
        product.getProductByCategory(ProductType.HAMBURGER.getType());
        product.getProductByCategory(ProductType.SET.getType());
        product.getProductByCategory(ProductType.SIDE.getType());
        product.getProductByCategory(ProductType.DRINK.getType());

        System.out.println("=================================");
        System.out.println("\n");
    }

    private String[] inputBuyProduct() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [치킨버거-3],[불고기버거세트-2])");
        String productList = input.getInput();

        return input.splitProductInput(productList);
    }

    private void buyProduct() {
        String[] items = inputBuyProduct();
        try {
            order.processOrder(UserScreen.connectedAccount(), items);
            showReceipt(items);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(ErrorMessage.PROBLEM_ORDER_PRODUCT.getMessage());
        }
    }

    private void showReceipt(String[] items) {
        int totalNum = 0;
        int totalPrice = 0;

        System.out.println("=====================");
        System.out.println("상품명 수량 금액");

        for (String item : items) {
            String[] parts = item.split(InputSign.PRODUCT_SEPARATOR.getSign());

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

        showAccountAssets(totalPrice);
    }

    private void showAccountAssets(int totalPrice) {
        String adminName = AdminScreen.connectedAccount();
        String userId = UserScreen.connectedAccount();

        int totalAdminAssets = admin.updateAdminAssets(adminName, totalPrice);
        int totalUserAssets = user.updateUserAssets(userId, totalPrice);

        System.out.println("판매자: " + adminName + ", " + totalAdminAssets);
        System.out.println("구매자: " + userId + ", " + totalUserAssets);
        System.out.println("\n");
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

        throw new IllegalArgumentException(ErrorMessage.INVALID_REBUY_OPTION.getMessage());


    }
}
