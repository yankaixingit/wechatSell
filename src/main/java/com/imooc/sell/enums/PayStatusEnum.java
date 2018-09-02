package com.imooc.sell.enums;

/**
 * author: 严凯新
 * date: 2018/9/2 19:40
 * version 1.0
 */
public enum PayStatusEnum {

    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功");

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
