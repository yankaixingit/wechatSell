package com.imooc.sell.result;

import com.imooc.sell.vo.CategoryVo;
import java.util.List;

/**
 * author: 严凯新
 * date: 2018/9/2 16:38
 * version 1.0
 */
public class ItemResult<T> extends DefaultResult{

    private List<T> data;

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
