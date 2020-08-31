package com.example.gateway;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author huangrui
 * @version 1.0
 * @description 异步线程，异常处理
 * @date 2019-01-09 00:17
 */
@Component
@Slf4j
@EnableAsync
public class AsyncPoolConfigurer implements AsyncConfigurer {

    /**
     * 不进行getAsyncExecutor()的复写，声明该线程池供全局适用，即声明式，编程式
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置异步线程名前缀
        executor.setThreadNamePrefix("App-Async-Executor");
        //设置最大线程数
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10);//超过corePoolSize后进入队列的大小
        executor.setKeepAliveSeconds(60);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60 * 15);
        /**
         * 拒绝策略：
         *  ThreadPoolExecutor.AbortPolicy:丢弃该任务并抛出RejectedExecutionException
         *  ThreadPoolExecutor.CallerRunsPolicy:由调用者线程处理
         *  ThreadPoolExecutor.DiscardOldesPolicy:丢弃线程队列中前面的任务，重试该任务 -》循环操作直至成功
         *  ThreadPoolExecutor.DiscardPolicy:丢弃该任务
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //自定义拒绝策略
//        executor.setRejectedExecutionHandler((callable,executor) -> {
//            log.error("异步线程池执行拒绝策略");
//            ...
//        });
        executor.initialize();
        return executor;
    }

    /**
     * 适用于声明式异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    @Bean
    public ScheduledThreadPoolExecutor asyncServiceExecutor() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setMaximumPoolSize(5);
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setUncaughtExceptionHandler(new MyExceptionHandler());
        threadFactoryBuilder.setNameFormat("yz-service-thread");
        executor.setThreadFactory(threadFactoryBuilder.build());
        return executor;
    }

    public static class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error("异步线程执行失败:{},e", t.getName(), e);
        }
    }
}
