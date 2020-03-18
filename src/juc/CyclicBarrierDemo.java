package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/25
 * @Content:
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * c(int parties, Runnable barrierAction)
         */
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for (int i = 0; i <7 ; i++) {
            final  int flag=i;
             new Thread(()->{
                 System.out.println(Thread.currentThread().getName()+" 当前第："+flag+"颗龙珠");
                 try {
                     cyclicBarrier.await();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (BrokenBarrierException e) {
                     e.printStackTrace();
                 }
             },String.valueOf(i)).start();
        }
    }
}
