package com.imooc.sell.dao;


import com.imooc.sell.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryDao extends JpaRepository<ItemCategory, Integer> {
}
