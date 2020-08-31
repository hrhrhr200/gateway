package com.example.gateway.exception;

import com.baitao.common.response.ResultCode;
import lombok.Data;

/**
 * @author hr
 */
@Data
public class BaseException extends RuntimeException {

    private IResponseEnum responseEnum;
    private Object[] args;
    private String msg;
    private Throwable cause;

    public BaseException(IResponseEnum responseEnum, Object[] args, String msg){
        this.responseEnum = responseEnum;
        this.args = args;
        this.msg = msg;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String msg, Throwable cause){
        this.responseEnum = responseEnum;
        this.args = args;
        this.msg = msg;
        this.cause = cause;
    }


}
