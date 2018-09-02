package com.imooc.sell.result;


/**
 * author: 严凯新
 * date: 2018/9/2 16:29
 * version 1.0
 */
public class DefaultResult<T> {

    private int code = 0;

    private String msg = "成功";

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
