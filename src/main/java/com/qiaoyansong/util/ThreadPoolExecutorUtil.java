package com.qiaoyansong.util;

import java.util.concurrent.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 19:19
 * description：
 */
public class ThreadPoolExecutorUtil {
    private static final int corePoolSize = 4;
    private static final int maximumPoolSize = Runtime.getRuntime().availableProcessors();
    private static final int keepAliveTime = 60 * 5;
    private static volatile ExecutorService SIMPLE_THREAD_POOL;
    private static volatile ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE;
    private ThreadPoolExecutorUtil(){

    }
    /**
     * 获取简单线程池
     */
    public static ExecutorService getSimpleThreadPool(){
        if (SIMPLE_THREAD_POOL == null){
            synchronized (ThreadPoolExecutorUtil.class){
                if(SIMPLE_THREAD_POOL == null){
                    SIMPLE_THREAD_POOL = new ThreadPoolExecutor(
                            corePoolSize,
                            maximumPoolSize,
                            keepAliveTime,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<>(5)
                    );
                }
                return SIMPLE_THREAD_POOL;
            }
        }else{
            return SIMPLE_THREAD_POOL;
        }
    }

    /**
     * 获取带有定时任务的线程池
     */
    public static ScheduledExecutorService getScheduledExecutorService(){
        if (SCHEDULED_EXECUTOR_SERVICE == null){
            synchronized (ThreadPoolExecutorUtil.class){
                if(SCHEDULED_EXECUTOR_SERVICE == null){
                    SCHEDULED_EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(maximumPoolSize);
                }
                return SCHEDULED_EXECUTOR_SERVICE;
            }
        }else{
            return SCHEDULED_EXECUTOR_SERVICE;
        }
    }
}
