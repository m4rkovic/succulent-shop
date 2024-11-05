package com.m4rkovic.succulent_shop.enumerator;

public enum OrderStatus {
    ORDERED("ORDERED"),
    PROCESSING("PROCESSING"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");
    private String code;

    private OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}