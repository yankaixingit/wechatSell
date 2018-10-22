package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * author: 严凯新
 * date: 2018/9/4 16:55
 * version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private Logger log = LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("严凯新");
        orderDto.setBuyerPhone("18370278725");
        orderDto.setBuyerAddress("深圳宝安固戍沙边路口");
        orderDto.setBuyerOpenid("yankaixin");
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setItemId("668556");
        orderDetail.setItemQuantity(3);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setItemId("26265");
        orderDetail2.setItemQuantity(2);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
        orderDto.setOrderDetailList(orderDetailList);
        orderService.create(orderDto);
    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("5246351539832326623");
        log.info("【查询单个订单】 result={}",orderDto);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtos = orderService.findList("yankaixin", pageRequest);

    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("5246351539832326623");
        orderService.cancel(orderDto);
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}