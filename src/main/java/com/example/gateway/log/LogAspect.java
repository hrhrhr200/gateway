package com.example.gateway.log;

import com.alibaba.fastjson.JSON;
import com.example.gateway.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

/**
 * @author LiuJiaPeng
 * @version 1.0
 * @description 可配合logRecord注解实现是否需要打印/不同级别打印
 * @date 2019-09-02 10:24
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut(value = "execution(* com.example.gateway.web..*.*(..))  || @annotation(LogRecord) || @within(LogRecord)")
    public void pointcut(){

    }


    @Before(value = "pointcut()")
    public void before(JoinPoint point){
        String msg = "#### parameter in url = %s , methodPath = %s.%s , parameters = %s";

        LogRecord logRecord = this.logRecord(point);
        if(logRecord != null && !logRecord.value()) {
            return;
        }

        if(logRecord != null
                && logRecord.position() != LogPosition.ALL
                && LogPosition.BEFORE != logRecord.position()){
            return;
        }
        try {
            String claName = point.getSignature().getDeclaringType().getName();
            String methodName = point.getSignature().getName();
            Object[] params = point.getArgs();
            params = Arrays.stream(params)
                    .filter(x->x != null && !BindingResult.class.isAssignableFrom(x.getClass()))
                    .toArray(Object[]::new);

            if(logRecord != null && !logRecord.url()){
                msg = "#### parameter in methodPath = %s.%s , parameters = %s";
                msg = String.format(msg, claName,methodName, JSON.toJSONString(params));
            }else{
                msg = String.format(msg, WebUtils.getRequest().getRequestURL().toString(),claName,methodName, JSON.toJSONString(params));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

        this.printLevel(msg,logRecord);
    }

    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void afterReturn(JoinPoint point, Object result){
        String msg = "#### parameter out url = %s , methodPath = %s.%s , result = %s";

        LogRecord logRecord = this.logRecord(point);
        if(logRecord != null && !logRecord.value()) {
            return;
        }

        if(logRecord != null
                && logRecord.position() != LogPosition.ALL
                && LogPosition.AFTER != logRecord.position()){
            return;
        }

        try {
            String claName = point.getSignature().getDeclaringType().getName();
            String methodName = point.getSignature().getName();

            if(logRecord != null && !logRecord.url()){
                msg = "#### parameter out methodPath = %s.%s , result = %s";
                msg = String.format(msg, claName,methodName, JSON.toJSONString(result));
            }else{
                msg = String.format(msg, WebUtils.getRequest().getRequestURL().toString(),claName,methodName, JSON.toJSONString(result));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

        this.printLevel(msg,logRecord);
    }

    private LogRecord logRecord(JoinPoint point){
        LogRecord claLogRecord = null;
        LogRecord mLogrecord = null;
        try {
            claLogRecord = (LogRecord) point.getSignature().getDeclaringType().getAnnotation(LogRecord.class);
            mLogrecord = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(LogRecord.class);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return mLogrecord != null ? mLogrecord : claLogRecord;
    }

    private void printLevel(String msg,LogRecord logRecord){
        if(logRecord == null) {
            log.info(msg);
            return;
        }

        switch (logRecord.level()){
            case TRACE:
                log.trace(msg);
                break;
            case DEBUG:
                log.debug(msg);
                break;
            case INFO:
                log.info(msg);
                break;
            case WARN:
                log.warn(msg);
                break;
            case ERROR:
                log.error(msg);
                break;
            default:
                log.info(msg);
        }
    }

}
