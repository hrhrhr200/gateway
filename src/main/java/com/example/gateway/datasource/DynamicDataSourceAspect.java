package com.example.gateway.datasource;

import com.baitao.common.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description 数据源动态切换AOP
 *              (提升数据源切换aop的执行顺序，使其优先于spring事务执行，可使该事务运行于特定数据源下
 *                  注：隐含问题,提升了数据源切换的执行顺序，在@After清理数据源后，事务提交是否存在找不到对应数据源操作。如果出现，可只提升@Before执行，降低@After执行，即将切换置于事务之前，清理置于事务之后。
 *              )
 * @Author LiuJiaPeng
 * @Date 2018-11-29 19:43
 */
@Aspect
@Component
@Order(value = 1)
public class DynamicDataSourceAspect {

    /*@Before("@within(com.baitao.yz.config.datasource.DataSource) || @annotation(com.baitao.yz.config.datasource.DataSource)")
    public void before(JoinPoint point){
        String dataSource = Constants.DEFAULT_DATASOURCE;
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        DataSource annotation = className.getAnnotation(DataSource.class);
        if(annotation != null){
            dataSource = annotation.value();
            DataSourceContextHolder.setDB(dataSource);
            return;
        }

        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        try {
            Method method = className.getMethod(methodName, argClass);
            annotation = method.getAnnotation(DataSource.class);
            if(annotation != null)
                dataSource = annotation.value();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@within(com.baitao.yz.config.datasource.DataSource) || @annotation(com.baitao.yz.config.datasource.DataSource)")
    public void after(){
        DataSourceContextHolder.clearDB();
    }*/

    @AfterThrowing("@within(com.example.gateway.datasource.DataSource) || @annotation(com.example.gateway.datasource.DataSource)")
    public void afterThrowing(){
        DataSourceContextHolder.clearDB();
    }

    @Around("@within(com.example.gateway.datasource.DataSource) || @annotation(com.example.gateway.datasource.DataSource)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String oldDataSource = DataSourceContextHolder.getDB();
        String newDataSource = "Constants.DEFAULT_DATASOURCE";
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        DataSource annotation = className.getAnnotation(DataSource.class);
        if(annotation != null){
            newDataSource = annotation.value();
            //DataSourceContextHolder.setDB(newDataSource);
            //return null;
        }
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        try {
            Method method = className.getMethod(methodName, argClass);
            annotation = method.getAnnotation(DataSource.class);
            if(annotation != null)
                newDataSource = annotation.value();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(newDataSource);

        Object o = point.proceed();

        if(StringUtils.isNotEmpty(oldDataSource) && !newDataSource.equals(oldDataSource)) {
            DataSourceContextHolder.setDB(oldDataSource);
        }
        return o;
    }
}
