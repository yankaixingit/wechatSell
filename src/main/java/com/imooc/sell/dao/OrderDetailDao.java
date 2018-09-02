package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/9/2 19:55
 * version 1.0
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
