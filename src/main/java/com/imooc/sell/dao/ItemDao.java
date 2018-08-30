package com.imooc.sell.dao;

import com.imooc.sell.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/8/30 16:37
 * version 1.0
 */
public interface ItemDao extends JpaRepository<Item,String> {

    List<Item> findByItemStatus(Integer itemStatus);

}
