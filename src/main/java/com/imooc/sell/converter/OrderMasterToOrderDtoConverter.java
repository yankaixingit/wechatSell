package com.imooc.sell.converter;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: 严凯新
 * date: 2018/10/18 11:46
 * version 1.0
 */
public class OrderMasterToOrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasters) {
        return orderMasters.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
