package com.example.gateway.datasource;


import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author LiuJiaPeng
 * @Date 2018-11-29 19:40
 */
@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() -> "Constants.DEFAULT_DATASOURCE");

    // 设置数据源名
    public static void setDB(String dbType) {
        log.info("切换到{}数据源",dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
