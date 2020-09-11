package com.example.gateway.utils;

import org.springframework.context.ApplicationContext;

/**
 * @author LiuJiaPeng
 * @version 1.0
 * @description TODO
 * @date 2019-10-08 16:12
 */
public class ApplicationContextUtils {

    private static ApplicationContext ctx;

    public static void setApplicationContext(ApplicationContext ctx) {
        if(ApplicationContextUtils.ctx == null){
            ApplicationContextUtils.ctx  = ctx;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return ApplicationContextUtils.ctx;
    }
}
