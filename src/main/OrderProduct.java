package main;

import main.entities.Product;
import main.entities.ProductDao;

public class OrderProduct {
    Input input = new Input();
    ProductDao product = new ProductDao();

    public void processOrderItems(String item) {
        // TODO: 매직넘버 처리
        // TODO: Complexity 해결
        String[] parts = item.split("-");
        String name = parts[0]; // 상품명
        int count = Integer.parseInt(parts[1]); // 구매 개수

        Product p = product.getProduct(name); // -한우버거, 10000원, 3개, 롯데리아 한우버거

        if (!validateProductName(p)) {
            return;
        }

        if ((isSide(p) || isDrink(p)) && validateProductQuantity(name, count)) {
            product.updateProductQuantity(name, count);
        }

        if (isBurger(p) && validateProductQuantity(name, count)) {
            product.updateProductQuantity(name, count);
            product.updateProductQuantity(name + "세트", count);
        }

        if (isBurgerSet(p) && validateProductQuantity(name, count) && validateSetComponentsQuantity(count)) {
            // TODO: 콜라, 제로콜라 중 선택 기능 추가
            product.updateProductQuantity(name, count);

            String updatedName = name.replace("세트", "");
            product.updateProductQuantity(updatedName, count);
            product.updateProductQuantity("감자튀김", count);
            product.updateProductQuantity("콜라", count);

        }
    }

    private boolean validateSetComponentsQuantity(int count) {
        // TODO: 감자튀김, 콜라 Enum으로 분리
        if (!validateProductQuantity("감자튀김", count) ||
                !validateProductQuantity("콜라", count)) {
            System.out.println("[ERROR] 세트 구성품(감자튀김/콜라)의 재고가 부족합니다.");
            return false;
        }
        return true;
    }

    private boolean validateProductName(Product p) {
        if (p == null) {
            System.out.println("[ERROR] 존재하지 않는 상품입니다.");
            return false;
        }
        return true;
    }

    private boolean validateProductQuantity(String productName, int buyQuantity) {
        Product p = product.getProduct(productName);

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
}
