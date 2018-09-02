package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

/**
 * author: 严凯新
 * date: 2018/9/2 20:23
 * version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void saveOrder(){
        OrderMaster order = new OrderMaster();
        order.setOrderId("128985");
        order.setBuyerName("严凯新");
        order.setBuyerPhone("18370278725");
        order.setBuyerAddress("深圳市固戍一路");
        order.setBuyerOpenid("526262");
        order.setOrderAmount(new BigDecimal("63.8"));
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());
        order.setDelete(false);
        orderMasterDao.save(order);
    }

    @Test
    public void findByBuyerOpenid() {

        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid("526262", pageRequest);

    }
}