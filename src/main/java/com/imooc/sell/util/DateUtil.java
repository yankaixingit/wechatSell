package com.imooc.sell.util;

import com.imooc.sell.util.serializer.TimestampToLongSerializer;

import java.sql.Timestamp;

/**
 * author: 严凯新
 * date: 2018/10/23 13:52
 * version 1.0
 */
public class DateUtil {

    /**
     * 时间戳转换成秒
     * @param timestamp
     * @return
     */
    public static Long TimestampToLong(Timestamp timestamp){
        Long time = Long.valueOf(timestamp.toString())/1000;
        return time;
    }
}
