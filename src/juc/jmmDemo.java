package juc;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/24
 * @Content:
 **/
class Mynumber{
     volatile int number=10;
     public  void  addTo1025(){
         this.number=1025;
    }
}

/**
 * JMM=可见性（通知机制）
 */
public class jmmDemo {
    public static void main(String[] args) {
        Mynumber mynumber=new Mynumber();
        new Thread(()->{
            System.out.println("come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mynumber.addTo1025();
            System.out.println(Thread.currentThread().getName()+"\t  update  number ,number value :"+mynumber.number);
        },"aaa").start();
        while (mynumber.number==10){

        }
        System.out.println(Thread.currentThread().getName()+"\t massion is over;");
    }
}
