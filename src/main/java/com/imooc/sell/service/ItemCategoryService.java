package com.imooc.sell.service;

import com.imooc.sell.entity.ItemCategory;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/8/30 14:26
 * version 1.0
 */
public interface ItemCategoryService {

    /**
     * 根据类目id查询类目信息
     * @param categoryId
     * @return
     */
    ItemCategory findOne(Integer categoryId);

    /**
     * 查询所有类目信息
     * @return
     */
    List<ItemCategory> findAll();

    /**
     * 根据类目编号查询类目集合
     * @param categoryNumberList
     * @return
     */
    List<ItemCategory> findListByCategoryNumber(List<Integer> categoryNumberList);

    /**
     * 保存类目信息
     * @param itemCategory
     * @return
     */
    ItemCategory saveItemCategory(ItemCategory itemCategory);
}
