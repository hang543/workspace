package listner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/3/2
 * @Content:
 **/
public class ThreadTestDemo {
    /**
     * 线程死循环演示
     */
    public static void creareBusyThread() {
        new Thread(() -> {
            while (true) ;
        }, "testBusyThread").start();
    }

    /**
     * 线程等待演示
     */
    public static void createLockThread(final Object lock) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread").start();
    }
    /**
     * 死锁等待演示
     */
    static class SynAddRunable implements Runnable{
        int a,b;

        public SynAddRunable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
//        bufferedReader.readLine();
//        creareBusyThread();
//        bufferedReader.readLine();
//        Object o = new Object();
//        createLockThread(o);
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();

        }
    }
}
