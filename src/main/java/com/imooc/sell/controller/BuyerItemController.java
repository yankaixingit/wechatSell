package com.imooc.sell.controller;

import com.imooc.sell.entity.Item;
import com.imooc.sell.entity.ItemCategory;
import com.imooc.sell.result.ItemResult;
import com.imooc.sell.service.ItemCategoryService;
import com.imooc.sell.service.ItemService;
import com.imooc.sell.util.ResultVOUtil;
import com.imooc.sell.vo.CategoryVo;
import com.imooc.sell.vo.ItemVo;
import com.imooc.sell.vo.ResultVO;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: 严凯新
 * date: 2018/9/2 16:23
 * version 1.0
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 买家商品列表
     * @return
     */
    @GetMapping("/list")
    public ResultVO list() {
        List<CategoryVo> categoryVos = new ArrayList<>();
        //查询所有的上架商品
        List<Item> items = itemService.findUpAll();
        //查询类目
        //精简方法（java8 ,lambda）
        List<Integer> categoryNumberList = items.stream().map(e -> e.getCategoryNumber()).collect(Collectors.toList());
        List<ItemCategory> itemCategoryList = itemCategoryService.findListByCategoryNumber(categoryNumberList);

        //数据拼装
        for (ItemCategory itemCategory : itemCategoryList) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setName(itemCategory.getCategoryName());
            categoryVo.setType(itemCategory.getCategoryNumber());

            List<ItemVo> itemVos = new ArrayList<>();
            for (Item item : items) {
                if(itemCategory.getCategoryNumber().equals(item.getCategoryNumber())){
                    ItemVo itemVo = new ItemVo();
                    BeanUtils.copyProperties(item,itemVo);
                    /*itemVo.setId(item.getItemId());
                    itemVo.setDescription(item.getItemDescription());
                    itemVo.setIcon(item.getItemIcon());
                    itemVo.setName(item.getItemName());
                    itemVo.setPrice(item.getItemPrice());*/
                    itemVos.add(itemVo);
                }

            }
            categoryVo.setFoods(itemVos);
            categoryVos.add(categoryVo);
        }
        return ResultVOUtil.success(categoryVos);
    }
}
