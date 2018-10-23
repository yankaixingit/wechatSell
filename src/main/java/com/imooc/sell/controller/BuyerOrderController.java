package com.imooc.sell.controller;

import com.imooc.sell.converter.OrderFormToOrderDtoConverter;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ExceptionEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.util.ResultVOUtil;
import com.imooc.sell.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: 严凯新
 * date: 2018/10/22 16:46
 * version 1.0
 */

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private Logger log = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ExceptionEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFormToOrderDtoConverter.convert(orderForm);
        OrderDto dto = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", dto.getOrderId());
        return ResultVOUtil.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ExceptionEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtos = orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderDtos.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDto orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
