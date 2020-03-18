package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/22
 * @Content: 现在两个线程 可以操作一个初始值为零的一个变量
 * 实现一个线程对该组变量加1 一个线程对该变量减一
 * 实现交替 来10轮 初始值为0
 * 1. 高聚底合的前提下，线程操作资源类
 * 2. 判断 干活 通知
 * 3. 防止虚假唤醒
 **/
class Aircondition {
    private int number = 0;
//   public   synchronized void increment()throws Exception{
//       //1.判断
//       while(number!=0){
//           this.wait();
//       }
//       //2.干活
//        number++;
//       System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //3.通知
//       this.notifyAll();
//    }
//    public synchronized void decrement()throws Exception{
//        //1.判断
//        while(number==0){
//            this.wait();
//        }
//        //2.干活
//        number--;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //3.通知
//        this.notifyAll();
//    }

    private Lock lock = new ReentrantLock();
    private Condition condition= lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
//             1.判断
            while (number != 0) {
            condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知
             condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void decrement() throws Exception {
        lock.lock();
        try {
//             1.判断
            while (number == 0) {
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class demo4 {
    public static void main(String[] args) throws Exception {
        Aircondition aircondition = new Aircondition();
        testMuiltThred(aircondition);
    }

    private static void testMuiltThred(Aircondition aircondition) {
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "c").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "D").start();
    }
}
