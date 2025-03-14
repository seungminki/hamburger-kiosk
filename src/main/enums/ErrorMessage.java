package main.enums;

public enum ErrorMessage {
    INVALID_PRODUCT("[ERROR] 존재하지 않는 상품입니다."),
    OVER_PRODUCT_QUANTITY("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다."),
    SOLDOUT_PRODUCT("[ERROR] 품절된 상품은 구매할 수 없습니다."),
    OVER_HAMBURGER_SET_QUANTITY("[ERROR] 세트 구성품(감자취김/콜라)의 재고 수량이 부족합니다."),
    INVALID_USER("[ERROR] 존재하지 않는 회원입니다."),
    INVALID_ADMIN("[ERROR] 존재하지 않는 관리자입니다."),
    ALREADY_USER("[ERROR] 이미 존재하는 회원 번호입니다."),
    ALREADY_ADMIN("[ERROR] 이미 존재하는 관리자 이름입니다."),
    INVALID_ROOT_MENU_NUM("[ERROR] 유효한 메뉴 번호를 입력하세요."),
    UNLOGGED_STATUS_ADMIN("[ERROR] 관리자 로그인 후 이용해주세요."),
    UNLOGGED_STATUS_USER("[ERROR] 회원 로그인 후 이용해주세요."),
    OVER_USER_ASSETS("[ERROR] 회원의 보유 금액을 초과하여 구매할 수 없습니다."),
    INVALID_REBUY_OPTION("[ERROR] 재구매 여부는 y/n 으로 선택하여 주십시오."),
    PROBLEM_ORDER_PRODUCT("[ERROR] 주문 처리 중 오류가 발생했으므로 재 주문 바랍니다");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}