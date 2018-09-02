package com.imooc.sell.vo;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/9/2 17:01
 * version 1.0
 */
public class CategoryVo {

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目类型
     */
    private Integer type;

    /**
     * 商品列表
     */
    private List<ItemVo> foods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ItemVo> getFoods() {
        return foods;
    }

    public void setFoods(List<ItemVo> foods) {
        this.foods = foods;
    }
}
