package com.imooc.sell.service.impl;

import com.imooc.sell.dao.ItemCategoryDao;
import com.imooc.sell.entity.ItemCategory;
import com.imooc.sell.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * author: 严凯新
 * date: 2018/8/30 14:36
 * version 1.0
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryDao itemCategoryDao;

    @Override
    public ItemCategory findOne(Integer categoryId) {
        return itemCategoryDao.findOne(categoryId);
    }

    @Override
    public List<ItemCategory> findAll() {
        return itemCategoryDao.findAll();
    }

    @Override
    public List<ItemCategory> findListByCategoryNumber(List<Integer> categoryNumberList) {
        return itemCategoryDao.findByCategoryNumberIn(categoryNumberList);
    }

    @Override
    public ItemCategory saveItemCategory(ItemCategory itemCategory) {
        return itemCategoryDao.save(itemCategory);
    }
}
