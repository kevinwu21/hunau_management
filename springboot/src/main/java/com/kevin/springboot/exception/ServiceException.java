package com.kevin.springboot.exception;

import lombok.Getter;

/** 自定义异常处理
 * @author: KevinWu
 * 2022/6/14 9:45 PM
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code,String msg){
        super(msg);
        this.code=code;
    }
}
