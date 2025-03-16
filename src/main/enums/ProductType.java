package main.enums;

public enum ProductType {
    HAMBURGER("햄버거"),
    SIDE("사이드"),
    DRINK("음료수"),
    SET("세트"),
    FRENCH_FRIES("감자튀김"),
    COKE("콜라"),
    ZERO_COKE("제로콜라");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}