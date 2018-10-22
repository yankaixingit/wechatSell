package com.imooc.sell.service.impl;

import com.imooc.sell.dao.ItemDao;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.entity.Item;
import com.imooc.sell.enums.ExceptionEnum;
import com.imooc.sell.enums.ItemEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: 严凯新
 * date: 2018/8/30 22:25
 * version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item findOne(String itemId) {
        return itemDao.findOne(itemId);
    }

    @Override
    public List<Item> findUpAll() {
        return itemDao.findByItemStatus(ItemEnum.UP.getCode());
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemDao.findAll(pageable);
    }

    @Override
    public Item save(Item item) {
        return itemDao.save(item);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            Item item = itemDao.findOne(cartDto.getItemId());
            if (item == null) {
                throw new SellException(ExceptionEnum.ITEM_NOT_EXIST);
            }
            Integer result = item.getItemStock() + cartDto.getItemQuantity();
            item.setItemStock(result);
            itemDao.save(item);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            Item item = itemDao.findOne(cartDto.getItemId());
            if (item == null) {
                throw new SellException(ExceptionEnum.ITEM_NOT_EXIST);
            }
            Integer result = item.getItemStock() - cartDto.getItemQuantity();
            if (result < 0){
                throw new SellException(ExceptionEnum.ITEM_STOCK_LACK);
            }
            item.setItemStock(result);
            itemDao.save(item);
        }
    }
}
