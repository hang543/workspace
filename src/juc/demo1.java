package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/21
 * @Content: 题目 三个售票员 卖出 30张票
 * 笔记 如何编写企业级的多线程代码
 * 固定的编程套路+模板是什么
 * 1.在高内聚低耦合的前提下 线程   操作  资源类
 * 1.1一言不合 先创建一个资源类
 * 1.2判断干活通知
 * 1.3多线程交互中 必须防止虚假唤醒 必须用while 不能用if
 * 1.4标志位
 **/
class Ticket //资源类 = 实例变量+实例方法
{
    private int number = 30;
    //List list=new ArrayList();
    /**
     * 可重用锁
     */
    Lock lock = new ReentrantLock();



    public void sale() {
        lock.lock();
        try {
            if (number > 0) {

                System.out.println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t 还剩下：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        
    }
}

public class demo1 {
    /**
     * .start不会立刻被启动 由底层CPU 分配后才启动
     *
     * @param args
     */
    public static void main(String[] args) //主线程，一切程序的入口
    {
        Ticket ticket = new Ticket();
        //Thread(Runnable target,String name) Allocates a new  Thread object.
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}
