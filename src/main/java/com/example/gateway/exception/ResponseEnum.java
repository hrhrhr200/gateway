package com.example.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by hrhrh on 2020/4/29 15:00
 */
@Getter
@AllArgsConstructor
public enum  ResponseEnum implements BusinessExceptionAssert {

    /**
     * Bad licence type
     */
    BAD_LICENCE_TYPE(7001, "Bad licence type."),
    /**
     * Licence not found
     */
    LICENCE_NOT_FOUND(7002, "Licence not found.")
    ;
    ;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
}
