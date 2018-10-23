package com.imooc.sell.service.impl;

import com.imooc.sell.controller.BuyerOrderController;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ExceptionEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: 严凯新
 * date: 2018/10/23 14:45
 * version 1.0
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private Logger log = LoggerFactory.getLogger(BuyerServiceImpl.class);

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if(orderDto==null){
            log.error("【取消订单】查不到该订单，orderId={}",orderId);
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto==null){
            return null;
        }
        if(!orderDto.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】订单的openid不一致，openid={},orderDto={}",openid,orderDto);
            throw new SellException(ExceptionEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
