package com.example.gateway.client;

import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.ssl.SSLUtils;
import com.dtflys.forest.utils.RequestNameValue;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrhrh on 2020/8/17 14:08
 */
@Configuration
@Data
public class AutoForestConfiguration {

    @Bean(name = "forest")
    public ForestConfiguration setForestConfiguration() {
        ForestConfiguration configuration = ForestConfiguration.configuration();
        configuration.setBackendName("okhttp3");
        // 连接池最大连接数，默认值为500
        configuration.setMaxConnections(123);
        // 每个路由的最大连接数，默认值为500
        configuration.setMaxRouteConnections(222);
        // 请求超时时间，单位为毫秒, 默认值为3000
        configuration.setTimeout(3000);
        // 连接超时时间，单位为毫秒, 默认值为2000
        configuration.setConnectTimeout(2000);
        // 请求失败后重试次数，默认为0次不重试
        //configuration.setRetryCount(3);
        // 这里setRetryCount只是简单机械的请求失败后的重试次数，所以一般建议设置为0。
        // 如果一定要多次重试，请一定要在保证服务端的幂等性的基础上进行重试，否则容易引发生产事故！
        // 单向验证的HTTPS的默认SSL协议，默认为SSLv3
        configuration.setSslProtocol(SSLUtils.SSLv3);
        // 打开或关闭日志，默认为true
        configuration.setLogEnabled(true);

        //设置全局header
        RequestNameValue r = new RequestNameValue("merchId","2", false);
        List<RequestNameValue> valueList = new ArrayList<>(1);
        valueList.add(r);
        configuration.setDefaultHeaders(valueList);
        return configuration;
    }
}
