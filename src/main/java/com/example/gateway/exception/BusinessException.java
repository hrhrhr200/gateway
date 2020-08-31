package com.example.gateway.exception;

import com.baitao.common.response.ResultCode;

/**
 * Created by hrhrh on 2020/4/29 14:51
 */
public class BusinessException extends BaseException {


    public BusinessException(IResponseEnum responseEnum, Object[] args, String msg) {
        super(responseEnum, args, msg);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String msg, Throwable cause) {
        super(responseEnum, args, msg, cause);
    }
}
