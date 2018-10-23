package com.imooc.sell.exception;

import com.imooc.sell.enums.ExceptionEnum;

/**
 * author: 严凯新
 * date: 2018/9/4 9:41
 * version 1.0
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
