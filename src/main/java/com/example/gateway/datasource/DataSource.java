package com.example.gateway.datasource;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 数据源配置
 * @Author LiuJiaPeng
 * @Date 2018-11-29 19:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DataSource {

    String value() default "Constants.DEFAULT_DATASOURCE";

}

