package com.example.gateway.datasource;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author LiuJiaPeng
 * @Date 2018-11-29 19:22
 */
@Configuration
@ConditionalOnProperty(name = "multi-data-source.open" , havingValue = "true")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dbProperties;

    //数据源会员
    @Bean(name = Constants.DATASOURCE_USER)
    public DataSource datasource_user(Environment env) {
        HikariConfig config = this.hikariConfig();
        config.setCatalog(env.getProperty("multi-data-source.User.catalog"));
        return new HikariDataSource(config);
    }

    //数据源订单
    @Bean(name = Constants.DATASOURCE_ORDER)
    public DataSource datasource_order(Environment env) {
        HikariConfig config = this.hikariConfig();
        config.setCatalog(env.getProperty("multi-data-source.order.catalog"));
        return new HikariDataSource(config);
    }

    //数据源商品
    @Bean(name = Constants.DATASOURCE_GOODS)
    public DataSource datasource_goods(Environment env) {
        HikariConfig config = this.hikariConfig();
        config.setCatalog(env.getProperty("multi-data-source.goods.catalog"));
        return new HikariDataSource(config);
    }

    //数据源基础数据
    @Bean(name = Constants.DATASOURCE_BASICS)
    public DataSource datasource_basics(Environment env) {
        HikariConfig config = this.hikariConfig();
        config.setCatalog(env.getProperty("multi-data-source.basics.catalog"));
        return new HikariDataSource(config);
    }


    private HikariConfig hikariConfig(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbProperties.getUrl());
        config.setDriverClassName(dbProperties.getDriverClassName());
        config.setUsername(dbProperties.getUsername());
        config.setPassword(dbProperties.getPassword());
        config.setConnectionTestQuery(dbProperties.getHikari().getConnectionTestQuery());
        config.setMinimumIdle(dbProperties.getHikari().getMinimumIdle());
        config.setMaximumPoolSize(dbProperties.getHikari().getMaximumPoolSize());
        config.setCatalog(dbProperties.getHikari().getCatalog());
        return config;
    }


    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier(Constants.DATASOURCE_USER) DataSource user,
                                        @Qualifier(Constants.DATASOURCE_ORDER) DataSource order,
                                        @Qualifier(Constants.DATASOURCE_GOODS) DataSource goods,
                                        @Qualifier(Constants.DATASOURCE_BASICS) DataSource basics) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(order);
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put(Constants.DATASOURCE_USER,user);
        dsMap.put(Constants.DATASOURCE_ORDER,order);
        dsMap.put(Constants.DATASOURCE_GOODS,goods);
        dsMap.put(Constants.DATASOURCE_BASICS,basics);
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
