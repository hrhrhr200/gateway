package com.example.gateway.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description Web工具类
 * @Author Huangrui
 * @Date 2018/9/11 15:34
 * @Version 1.0
 */
@Slf4j
public class WebUtils {

    private WebUtils(){}

    /**
     * @Author LiuJiaPeng
     * @Description 获取HttpServletRequest
     * @Date 15:40 2018/9/11
     * @Param
     * @Return
     */
    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes != null && requestAttributes instanceof ServletRequestAttributes ?
                ((ServletRequestAttributes)requestAttributes).getRequest() : null;
    }

    /**
     * @Author LiuJiaPeng
     * @Description 获取HttpServletResponse
     * @Date 15:41 2018/9/11
     * @Param
     * @Return
     */
    public static HttpServletResponse getResponse(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes != null && requestAttributes instanceof ServletRequestAttributes ?
                ((ServletRequestAttributes)requestAttributes).getResponse() : null;
    }

    public static byte[] getBody(){
        HttpServletRequest request = getRequest();
        ServletInputStream sis = null;
        byte[] bytes = null;
        int n = -1;
        try {
            int size = request.getContentLength();
            sis = request.getInputStream();
            bytes = new byte[size];
            n = sis.read(bytes,0,size);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        } finally {
            if(sis != null){
                try {
                    sis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return n >= 0 ? bytes : "{}".getBytes();
    }

    /**
     * @Description 获取token
     * @Author LiuJiaPeng
     * @Date 2018/10/31 23:48
     * @Param
     * @Return java.lang.String
     */
    public static String getToken(){
        return getRequest().getHeader(HttpHeaders.AUTHORIZATION);
    }

    /**
     * @Description response响应
     * @Author LiuJiaPeng
     * @Date 2018/9/28 0:08
     * @Param message
     * @Param status
     * @Return void
     */
    public static void writeResponse(String message, HttpStatus status){
        if(message == null)
            return;
        HttpServletResponse res = getResponse();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(status.value());
        ServletOutputStream outputStream = null;
        try {
            outputStream = res.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

//            res.getWriter().write(message);
//            res.getWriter().flush();
//            res.getWriter().close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("",e);
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("",e);
                }
            }

        }
    }


}
