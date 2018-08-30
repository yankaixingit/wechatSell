package com.imooc.sell.dao;


import com.imooc.sell.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCategoryDao extends JpaRepository<ItemCategory, Integer> {

    List<ItemCategory> findByCategoryNumberIn(List<Integer> integerList);
}
