package com.example.gateway;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by hrhrh on 2019/9/26 18:18
 */
@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.error(throwable.getMessage(),throwable);
        log.error("异步线程执行异常: {}.{}\n参数为：{}",method.getDeclaringClass().getName(),method.getName(), JSON.toJSONString(objects));
//        this.dealException(throwable,method,objects);
    }


/*
    private void dealException(Throwable throwable,Method method,Object... objects){
        if(throwable instanceof SaleOrderException) {
            YzOrderFail yzOrderFail = new YzOrderFail();
            SaleOrderMsg saleOrderMsgs = (SaleOrderMsg) objects[0];
            yzOrderFail.setId(ObjectId.get().toString());
            yzOrderFail.setTid(saleOrderMsgs.getFullOrderInfo().getOrderInfo().getTid());
            yzOrderFail.setMsg(((SaleOrderException) throwable).getMsg());
            yzOrderFail.setCreateDate(new Date());

            ApplicationContext ctx = ApplicationContextUtils.getApplicationContext();
            YzOrderFailRepository yzOrderFailRepository = ctx.getBean(YzOrderFailRepository.class);
            yzOrderFailRepository.insert(yzOrderFail);
        }
*/

//        if (throwable instanceof BusinessException){
//            BusinessExceptionAnno anno = method.getAnnotation(BusinessExceptionAnno.class);
//            if(anno != null && anno.type() != null){
//                BusinessExceptionRecord record = new BusinessExceptionRecord();
//                record.setPath(method.getDeclaringClass().getName() + "." + method.getName());
//                record.setParams(JSONObject.toJSONString(objects));
//                record.setType(anno.type().getValue());
//                record.setErrorMsg(throwable.getMessage());
//                record.setCreatedDate(new Date());
//                mongoTemplate.save(record);
//            }
//        }

    //}
}
