package main.screens;

import main.Input;
import main.entities.*;

import java.io.IOException;

public class ProductOrder {
    Input input = new Input();
    ProductDao product = new ProductDao();
    AdminDao admin = new AdminDao();
    UserDao user = new UserDao();

    private boolean validateProductName(Product p) {
        if (p == null) {
            System.out.println("해당 상품이 없습니다.");
            return false;
        }
        return true;
    }

    private boolean validateProductQuantity(Product p, int buyQuantity) {
        ;
        try {
            int currentQuantity = Integer.parseInt(p.getQuantity());

            if (currentQuantity > buyQuantity) {
                return true;
            } else {
                System.out.println("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 품절된 상품은 구매할 수 없습니다.");
            return false;
        }
    }

    private boolean validateProductAssets(int userAssets, int totalPrice) {
        // TODO: 회원의 보유 금액과 총 물품 가격을 비교하여 에러 발생
        if (userAssets > totalPrice) {
            return true;
        }
        return false;

    }
    
    private void buyProduct() {
        while (true) {
            System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [치킨버거-3],[불고기버거세트-2])");
            String productList = input.getInput();
            String[] items = input.splitProductInput(productList);

            for (String item : items) {
                String[] parts = item.split("-");
                String name = parts[0]; // 상품명
                int count = Integer.parseInt(parts[1]); // 개수


                Product p = product.getProduct(name); // -한우버거, 10000원, 3개, 롯데리아 한우버거

                if (!validateProductName(p)) {
                    break;
                }

                if (isSide(p) || isDrink(p)) {
                    if (validateProductQuantity(p, count)) {
                        product.updateProductQuantity(name, count);
                    }
                }

                if (isBurger(p)) {
                    // System.out.println("너 지금 버거 시킴");

                    if (validateProductQuantity(p, count)) {
                        product.updateProductQuantity(name, count);
                        product.updateProductQuantity(name + "세트", count);
                    }
                }

                if (isBurgerSet(p)) {
                    // System.out.println("너 지금 세트 시킴");

                    if (validateProductQuantity(p, count)) {
                        if (validateProductQuantity(p, count)) {
                            if (validateProductQuantity(p, count)) {
                                product.updateProductQuantity(name, count);

                                String updatedName = name.replace("세트", "");

                                System.out.println(name + " -> " + updatedName); // 치킨버거세트 -> 치킨버거
                                product.updateProductQuantity("감자튀김", count);
                                product.updateProductQuantity("콜라", count);
                            }
                        }
                    }

                }
            }
            resultRecipt(items);
            // TODO: 수량 및 에러 발생 시 영수증 표시 X
            break;
        }
    }

    private boolean isBurger(Product p) {
        return p.getCategory().equals("햄버거");

    }

    private boolean isBurgerSet(Product p) {
        return p.getCategory().equals("세트");
    }

    private boolean isSide(Product p) {
        return p.getCategory().equals("사이드");
    }

    private boolean isDrink(Product p) {
        return p.getCategory().equals("음료수");
    }

    private void resultRecipt(String[] items) {
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
}