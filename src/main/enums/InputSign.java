package main.enums;

public enum InputSign {
    ACCOUNT_SEPARATOR(" "),

    PRODUCT_REGEX("[\\[\\]]"),
    PRODUCT_SEPARATOR("-"),

    DELIMITER(","),
    QUOTATION("\""),
    EMPTY("");

    private final String sign;

    InputSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
