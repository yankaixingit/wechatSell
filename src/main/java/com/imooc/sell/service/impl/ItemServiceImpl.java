package com.imooc.sell.service.impl;

import com.imooc.sell.dao.ItemDao;
import com.imooc.sell.entity.Item;
import com.imooc.sell.enums.ItemEnum;
import com.imooc.sell.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/8/30 22:25
 * version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item findOne(String itemId) {
        return itemDao.findOne(itemId);
    }

    @Override
    public List<Item> findUpAll() {
        return itemDao.findByItemStatus(ItemEnum.UP.getCode());
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemDao.findAll(pageable);
    }

    @Override
    public Item save(Item item) {
        return itemDao.save(item);
    }
}
