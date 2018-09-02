package com.imooc.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * author: 严凯新
 * date: 2018/9/2 16:39
 * version 1.0
 */
public class ItemVo {

    @JsonProperty("id")
    private String itemId;

    @JsonProperty("name")
    private String itemName;

    @JsonProperty("price")
    private BigDecimal itemPrice;

    @JsonProperty("description")
    private String itemDescription;

    @JsonProperty("icon")
    private String itemIcon;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(String itemIcon) {
        this.itemIcon = itemIcon;
    }
}
