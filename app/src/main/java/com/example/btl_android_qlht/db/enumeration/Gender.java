package com.example.btl_android_qlht.db.enumeration;

public enum Gender {
    FEMALE(0, "Nữ"),

    MALE(1, "Nam");

    private final int key;
    private final String value;

    Gender(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
