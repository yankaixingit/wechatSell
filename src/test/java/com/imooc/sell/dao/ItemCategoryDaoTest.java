package com.imooc.sell.dao;

import com.imooc.sell.entity.ItemCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemCategoryDaoTest {

    @Autowired
    private ItemCategoryDao itemCategoryDao;

    @Test
    public void findOneTest(){
        ItemCategory one = itemCategoryDao.findOne(1);
        System.out.println(one);
    }

    @Test
    public void saveTest(){
        ItemCategory entity = new ItemCategory();
        entity.setCategoryId(3);
        entity.setCategoryName("盛世嫡妃");
        entity.setCategoryNumber(6);
        itemCategoryDao.save(entity);
    }
}