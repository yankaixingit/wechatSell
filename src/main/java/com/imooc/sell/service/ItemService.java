package com.imooc.sell.service;

import com.imooc.sell.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/8/30 22:24
 * version 1.0
 */
public interface ItemService{

    Item findOne(String itemId);

    List<Item> findUpAll();

    Page<Item> findAll(Pageable pageable);

    Item save(Item item);
}
