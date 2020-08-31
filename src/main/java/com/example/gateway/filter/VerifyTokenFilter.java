package com.example.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.baitao.common.response.Result;
import com.baitao.common.util.ResultUtils;
import com.baitao.common.util.StringUtils;
import com.example.gateway.config.UrlConfig;
import com.example.gateway.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Created by hrhrh on 2020/4/26 11:33
 */
@Component
@Slf4j
public class VerifyTokenFilter extends ZuulFilter {

    @Resource
    private UrlConfig urlConfig;

    @Resource
    private JwtUtils jwtUtils;


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !urlConfig.getTokenUrl().equals(ctx.getRequest().getRequestURI());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = ctx.getRequest().getHeader("token");
        try{
            if(StringUtils.isEmpty(token)) {
                log.error("请求头中不包含token");
                ctx.setSendZuulResponse(false);
                responseError(ctx, ResultUtils.dataErrorResult("token missing"));
                return null;
            }
            jwtUtils.parseJWT(token);
            log.info("token : {} 验证通过", token);
            //对请求进行路由
            ctx.setSendZuulResponse(true);

        }catch (ExpiredJwtException e) {
            log.error("token : {} 过期", token );
            //不对请求进行路由
            ctx.setSendZuulResponse(false);
            responseError(ctx, ResultUtils.dataErrorResult("token expired"));
        }catch (Exception e) {
            log.error("e", e);
            ctx.setSendZuulResponse(false);
            responseError(ctx, ResultUtils.dataErrorResult("invalid token"));
        }
        return null;
    }

    private void responseError(RequestContext ctx, Result<?> result) {
        HttpServletResponse response = ctx.getResponse();
        ctx.setResponseBody(JSON.toJSONString(result));
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
    }
}
