package com.example.gateway.filter;

import com.baitao.common.response.Result;
import com.baitao.common.util.ResultUtils;
import com.example.gateway.config.ResultJacksonJsonParser;
import com.example.gateway.config.UrlConfig;
import com.example.gateway.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * Created by hrhrh on 2020/4/26 10:30
 */
@Component
@Slf4j
public class CreateTokenFilter extends ZuulFilter {

    @Resource
    private UrlConfig urlConfig;

    @Resource
    private ResultJacksonJsonParser jsonParser;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = ctx.getRequest().getRequestURI();
        return urlConfig.getTokenUrl().equals(requestURI);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        InputStream dataStream = ctx.getResponseDataStream();
        try {
            String body = StreamUtils.copyToString(dataStream, StandardCharsets.UTF_8);
            Result<Map<String, String>> parseResult = jsonParser.parseResult(body);
            if(!ResultUtils.isSuccess(parseResult)) {
                ctx.setResponseBody(body);
                return null;
            }
            Map<String, String> map = parseResult.getData();
            //过期时间设置

            String token = jwtUtils.createJWT(120000, map.get("shopId"));

            map.put("token", token);

            String nBody = jsonParser.getObjectMapper().writeValueAsString(parseResult);

            ctx.setResponseBody(nBody);

        } catch (IOException e) {
            log.error("e:", e);
        }
        return null;
    }
}
