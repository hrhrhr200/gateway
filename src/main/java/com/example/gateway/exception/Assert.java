package com.example.gateway.exception;

/**
 * Created by hrhrh on 2020/4/29 14:35
 */
public interface Assert {

    /**
     * 创建异常
     * @param args
     * @return
     */
    BaseException newException(Object... args);

    /**
     * 常见异常
     * @param t t
     * @param args
     * @return
     */
    BaseException newException(Throwable t, Object... args);

    default void assertNotNull(Object obj) {
        if(null == obj) {
            throw newException(obj);
        }
    }

    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
    }
}
