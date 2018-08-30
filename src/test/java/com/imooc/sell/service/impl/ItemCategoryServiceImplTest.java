package com.imooc.sell.service.impl;

import com.imooc.sell.entity.ItemCategory;
import com.imooc.sell.service.ItemCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * author: 严凯新
 * date: 2018/8/30 14:48
 * version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemCategoryServiceImplTest {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Test
    public void findOne() {
        ItemCategory one = itemCategoryService.findOne(1);
        System.out.println(one);


    }

    @Test
    public void findAll() {
    }

    @Test
    public void findListByCategoryNumber() {
    }

    @Test
    public void saveItemCategory() {
    }
}