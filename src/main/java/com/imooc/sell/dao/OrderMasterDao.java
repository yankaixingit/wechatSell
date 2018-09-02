package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author: 严凯新
 * date: 2018/9/2 19:54
 * version 1.0
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);
}
