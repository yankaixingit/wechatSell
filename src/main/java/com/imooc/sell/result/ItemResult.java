package com.imooc.sell.result;

import com.imooc.sell.vo.CategoryVo;
import java.util.List;

/**
 * author: 严凯新
 * date: 2018/9/2 16:38
 * version 1.0
 */
public class ItemResult extends DefaultResult{

    private ItemWrap data;

    @Override
    public ItemWrap getData() {
        return data;
    }

    public void setData(ItemWrap data) {
        this.data = data;
    }
}
