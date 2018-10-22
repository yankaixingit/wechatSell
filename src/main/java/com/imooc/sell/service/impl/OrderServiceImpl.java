package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMasterToOrderDtoConverter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: 严凯新
 * date: 2018/9/3 17:51
 * version 1.0
 */

@Service
public class OrderServiceImpl implements OrderService {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

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
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new SellException(ExceptionEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String openId, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenid(openId, pageable);
        List<OrderDto> convert = OrderMasterToOrderDtoConverter.convert(orderMasters.getContent());
        return new PageImpl<OrderDto>(convert, pageable, orderMasters.getTotalElements());
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster master = orderMasterDao.save(orderMaster);
        if (master == null) {
            log.error("【取消订单】更新订单失败，orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderDto={}", orderDto);
            throw new SellException(ExceptionEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getItemId(), e.getItemQuantity())
        ).collect(Collectors.toList());
        itemService.increaseStock(cartDtoList);
        //如果已支付，退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS)){
            //TODO
        }
        return orderDto;
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
