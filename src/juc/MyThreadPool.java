package juc;

import java.util.concurrent.*;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/26
 * @Content: 线程池
 **/
public class MyThreadPool {
    public static void main(String[] args) {
        /**
         * 一池五个受理线程，类似一个银行有五个受理窗口
         * 一池一线程
         * 一池N个线程
         */
        // ExecutorService threadPool= Executors.newFixedThreadPool(5);
        // ExecutorService threadPool= Executors.newSingleThreadExecutor();
        //executors();
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 10; i++) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    public static void executors() {
        ExecutorService threadPool = Executors.newCachedThreadPool();


        try {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
