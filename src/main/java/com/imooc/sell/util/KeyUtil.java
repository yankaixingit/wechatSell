package com.imooc.sell.util;

import java.util.Random;

/**
 * author: 严凯新
 * date: 2018/9/4 10:40
 * version 1.0
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     *
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return String.valueOf(number) + System.currentTimeMillis();
    }
}
