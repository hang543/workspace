package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/22
 * @Content:
 * 多线程之间的调用顺序 实现A->B->C
 * 三个线程启动 要求如下：
 *  AA打印5次 BB打印10次 CC打印15次
 *  接着
 *  AA打印5次 BB打印10次 CC打印15次
 *  来10轮
 **/
class ShareData{

    private Lock lock= new ReentrantLock();
    private Condition c1 =lock.newCondition();
    private Condition c2 =lock.newCondition();
    private Condition c3 =lock.newCondition();

    private  int  number=1;
    public  void  print5(Integer integer,Integer flag,Integer length){

        lock.lock();
        try{
            while (number!=integer){
                if (integer==1){
                    c1.await();
                }else if (integer==2){
                    c2.await();
                }else if (integer==3){
                    c3.await();
                }
            }


            for (int i = 0; i <length; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=flag;
            if (flag==1){ c1.signal();}
            if (flag==2){ c2.signal();}
            if (flag==3){ c3.signal();}


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }
//    public  void  print10(){
//        lock.lock();
//        try{
//            while (number!=2){
//                c2.await();
//            }
//            for (int i = 0; i <10; i++) {
//                System.out.println(Thread.currentThread().getName()+"\t"+i);
//            }
//            number=3;
//            c3.signal();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//    public  void  print15(){
//        lock.lock();
//        try{
//            while (number!=3){
//                c3.await();
//            }
//            for (int i = 0; i <15; i++) {
//                System.out.println(Thread.currentThread().getName()+"\t"+i);
//            }
//            number=1;
//            c1.signal();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
}
public class ConditionDemo {
    public static void main(String[] args) {
        ShareData shareData=new ShareData();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.print5(1,2,5);

            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.print5(2,3,10);

            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.print5(3,1,15);

            }
        },"C").start();
    }
}
