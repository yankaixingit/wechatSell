package com.imooc.sell.enums;

/**
 * author: 严凯新
 * date: 2018/9/4 10:10
 * version 1.0
 */
public enum ExceptionEnum {
    ITEM_NOT_EXIST(1001,"商品不存在"),
    ITEM_STOCK_LACK(1002,"商品库存不足"),
    ORDER_NOT_EXIST(1003,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(1004,"订单详情不存在"),
    ORDER_STATUS_ERROR(1005,"订单状态不正确"),
    ORDER_UPDATE_FAIL(1006,"订单更新失败"),
    ORDER_DETAIL_EMPTY(1007,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(1008,"订单支付状态不正确"),

    PARAM_ERROR(2001,"参数不正确")
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
