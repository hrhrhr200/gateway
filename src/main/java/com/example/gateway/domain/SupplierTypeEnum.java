package com.example.gateway.domain;

/**
 * Created by hrhrh on 2020/8/6 11:22
 */
public enum SupplierTypeEnum {

    AGENCY("代销"),
    DIST("经销"),
    ALLIANCE("联营")
    ;


    private String value;

    SupplierTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String name) {
        SupplierTypeEnum[] values = values();
        for(SupplierTypeEnum e : values) {
            if(e.name().equals(name)) {
                return e.value;
            }
        }

        return "";
    }
}
