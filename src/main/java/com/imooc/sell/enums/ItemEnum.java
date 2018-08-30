package com.imooc.sell.enums;

/**
 * author: 严凯新
 * date: 2018/8/30 22:33
 * version 1.0
 */
public enum ItemEnum {
    UP(0,"正常"),
    DOWM(1,"下架")
    ;

    private Integer code;

    private String message;

    ItemEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
