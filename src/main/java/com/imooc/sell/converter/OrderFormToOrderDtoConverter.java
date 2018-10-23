package com.imooc.sell.converter;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.form.ItemForm;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.util.JsonUtil;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * author: 严凯新
 * date: 2018/10/22 17:22
 * version 1.0
 */
public class OrderFormToOrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<ItemForm> itemForms = JsonUtil.jsonToList(orderForm.getItems(), ItemForm.class);
        if(!CollectionUtils.isEmpty(itemForms)){
            for(ItemForm itemForm:itemForms){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setItemId(itemForm.getProductId());
                orderDetail.setItemQuantity(itemForm.getProductQuantity());
                orderDetails.add(orderDetail);
            }
        }
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }
}
