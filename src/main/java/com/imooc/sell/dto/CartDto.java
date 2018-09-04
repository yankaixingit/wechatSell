package com.imooc.sell.dto;

/**
 * author: 严凯新
 * date: 2018/9/4 11:33
 * version 1.0
 */
public class CartDto {

    private String itemId;

    private Integer itemQuantity;

    public CartDto(String itemId, Integer itemQuantity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
