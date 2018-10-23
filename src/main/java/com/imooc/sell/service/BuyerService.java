package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;

/**
 * author: 严凯新
 * date: 2018/10/23 14:43
 * version 1.0
 */
public interface BuyerService {

    //查询一个订单
    OrderDto findOrderOne(String openid,String orderId);

    //取消订单
    OrderDto cancelOrder(String openid,String orderId);
}
