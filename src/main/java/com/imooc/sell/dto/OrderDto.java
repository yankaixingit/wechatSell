package com.imooc.sell.dto;

import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/9/3 17:32
 * version 1.0
 */
public class OrderDto extends OrderMaster {

    List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
