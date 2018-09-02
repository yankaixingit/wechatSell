package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * author: 严凯新
 * date: 2018/9/2 21:01
 * version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveDetail(){
        OrderDetail orderDetail = new OrderDetail();;
        orderDetail.setDetailId("451515");
        orderDetail.setOrderId("123456");
        orderDetail.setItemId("78454");
        orderDetail.setItemName("皮皮虾");
        orderDetail.setItemPrice(new BigDecimal("15.30"));
        orderDetail.setItemQuantity(1);
        orderDetail.setItemIcon("http:xxx.jpg");
        orderDetail.setDelete(false);
        orderDetailDao.save(orderDetail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("123456");
        System.out.println(orderDetailList.size());
    }
}