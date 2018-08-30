package com.imooc.sell.dao;

import com.imooc.sell.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        item.setItemId("668556");
        item.setItemName("汉堡包");
        item.setItemPrice(new BigDecimal("6.55"));
        item.setItemStock(100);
        item.setItemDescription("好吃的一匹!");
        item.setItemIcon("http://xxx.jpg");
        item.setItemStatus(0);
        item.setCategoryNumber(332);
        item.setDelete(false);
        itemDao.save(item);
    }

    @Test
    public void findByItemStatus() {
    }
}