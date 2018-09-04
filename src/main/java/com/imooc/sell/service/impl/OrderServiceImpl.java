package com.imooc.sell.service.impl;

import com.imooc.sell.dao.OrderDetailDao;
import com.imooc.sell.dao.OrderMasterDao;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.entity.Item;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.enums.ExceptionEnum;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ItemService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: 严凯新
 * date: 2018/9/3 17:51
 * version 1.0
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private ItemService itemService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            Item item = itemService.findOne(orderDetail.getItemId());
            if (item == null) {
                throw new SellException(ExceptionEnum.ITEM_NOT_EXIST);
            }
            //2.计算总价
            orderAmount = item.getItemPrice()
                    .multiply(new BigDecimal(orderDetail.getItemQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setItemName(item.getItemName());
            orderDetail.setItemPrice(item.getItemPrice());
            orderDetail.setItemIcon(item.getItemIcon());
            orderDetail.setDelete(false);
            orderDetailDao.save(orderDetail);
        }
        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setBuyerName(orderDto.getBuyerName());
        orderMaster.setBuyerPhone(orderDto.getBuyerPhone());
        orderMaster.setBuyerAddress(orderDto.getBuyerAddress());
        orderMaster.setBuyerOpenid(orderDto.getBuyerOpenid());
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setDelete(false);
        orderMasterDao.save(orderMaster);
        //4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getItemId(), e.getItemQuantity()))
                .collect(Collectors.toList());
        itemService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String openId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
