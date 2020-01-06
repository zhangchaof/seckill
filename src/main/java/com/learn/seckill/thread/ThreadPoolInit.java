package com.learn.seckill.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: seckill:ThreadPoolInit
 * @description:
 * @author: zcf
 * @create: 2020-01-06 09:44
 **/
@Configuration
public class ThreadPoolInit {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 10;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final String THREAD_POOL_NAME = "ASYNC_TASK_EXECUTOR";


    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor get() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数 默认=1
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        // 最大线程数 默认 2147483647
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //  队列大小 默认 2147483647
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        taskExecutor.setThreadNamePrefix(THREAD_POOL_NAME);
        /**
         * rejectedExecutionHandler字段用于配置拒绝策略，常用的拒绝策略如下：
         *
         * AbortPolicy，用于被拒绝任务的处理程序，它将抛出RejectedExecutionException。
         * CallerRunsPolicy，用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
         * DiscardOldestPolicy，用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
         * DiscardPolicy，用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(KEEP_ALIVE_SECONDS);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
