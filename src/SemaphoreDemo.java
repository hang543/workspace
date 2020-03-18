import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/25
 * @Content:
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        for (int i = 0; i <6 ; i++) {
             new Thread(()->{
                 try {
                     semaphore.acquire();
                     System.out.println(Thread.currentThread().getName()+"\t 抢到了线程");
                     try {
                         TimeUnit.SECONDS.sleep(3);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println(Thread.currentThread().getName()+"\t 离开车位");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } finally {
                     semaphore.release();
                 }
             },String.valueOf(i)).start();


        }
    }
}
