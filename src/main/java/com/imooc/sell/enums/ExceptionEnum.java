package com.imooc.sell.enums;

/**
 * author: 严凯新
 * date: 2018/9/4 10:10
 * version 1.0
 */
public enum ExceptionEnum {
    ITEM_NOT_EXIST(1001,"商品不存在"),
    ITEM_STOCK_LACK(1002,"商品库存不足")
    ;

    ExceptionEnum(Integer code, String message) {
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
