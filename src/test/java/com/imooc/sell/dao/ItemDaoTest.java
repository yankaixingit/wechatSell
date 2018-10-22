package com.imooc.sell.dao;

import com.imooc.sell.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * author: 严凯新
 * date: 2018/8/30 16:48
 * version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDaoTest {

    @Autowired
    private ItemDao itemDao;

    @Test
    public void saveTest(){
        Item item = new Item();
        item.setItemId("26265");
        item.setItemName("煌上煌");
        item.setItemPrice(new BigDecimal("26.3"));
        item.setItemStock(10);
        item.setItemDescription("好吃真爽！");
        item.setItemIcon("http://zsfd.jpg");
        item.setItemStatus(0);
        item.setCategoryNumber(123);
        item.setDelete(false);
        itemDao.save(item);
        System.out.println(1/0);
    }

    @Test
    public void findByItemStatus() {
    }
}