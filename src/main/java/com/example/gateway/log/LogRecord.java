package com.example.gateway.log;

import java.lang.annotation.*;

/**
 * @author Huangrui
 * @version 1.0
 * @description 日志输出
 * @date 2019-09-02 11:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Inherited
public @interface LogRecord {

    /**
     * 打印级别
     */
    LogLevel level() default LogLevel.INFO;

    /**
     * 是否打印
     */
    boolean value() default true;

    /**
     * 打印位置
     */
    LogPosition position() default LogPosition.ALL;

    /**
     * 是否打印url
     */
    boolean url() default false;

}
