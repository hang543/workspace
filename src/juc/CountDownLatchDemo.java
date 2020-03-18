package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/25
 * @Content: 空值线程执行顺序
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 当前离开教室");
                countDownLatch.countDown();//计数
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 关门走人");
    }

    public static void closeDoor() {
        for (int i = 0; i <6 ; i++) {
             new Thread(()->{
                 System.out.println(Thread.currentThread().getName()+"\t 当前离开教室");
             },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t 关门走人");
    }
}
