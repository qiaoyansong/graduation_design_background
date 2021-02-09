package com.qiaoyansong.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 20:01
 * description：测试延迟任务
 */
public class TestScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPoolExecutor= Executors.newScheduledThreadPool(1);
        try {
            long cur = System.currentTimeMillis();
            System.out.println("延迟要开始了");
            scheduledThreadPoolExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello, World!");
                }
                //0表示首次执行任务的延迟时间，1000表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
            }, 2000, TimeUnit.MILLISECONDS);
            long stop = System.currentTimeMillis();

        }finally {
            scheduledThreadPoolExecutor.shutdown();
        }

    }
}
