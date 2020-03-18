package juc;

import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/21
 * @Content:
 * 8lock
 * 1.标准访问，先打印邮件还是打印短信
 * 2.暂停4秒实现打印邮件还是短信
 * 3.新增普通sayhello方法 是先发邮件 还是先sayhello
 * 4.两部手机先打印邮件还是短信
 * 5.两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6.两个静态同步方法，2部手机，请问先打印邮件还是短信
 * 7.1个静态同步方法，一个普通同步方法，同一部手机 请问先打印什么
 * 8.1个静态同步方法，一个普通同步方法，2部手机 请问先打印什么
 * 一个对象里如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了
 * 其他的线程只能等待，换句话说 在某一时刻，只能有唯一一个线程去访问这些synchronized方法
 * 锁 的是当前对象的this，被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
 *
 * static 锁的是全局锁
 *
 *  加个普通方法后发现合同步锁无关
 *  换成两个对象后 不是同一吧锁了 情况立刻变化
 *
 *  synchronized实现同步的基础：java中的每一个对象都可以作为锁
 *  具体表现为以下的3种形式
 *  对于普通的同步方法 锁的是当前实例对象
 *  对于同步方法块，锁的是synchronized括号里的配置对象
 *  对于静态同步方法 锁的是当前类的class对象
 *
 *  当一个线程试图访问同步代码块时，它首先必须得到锁，退出或者抛出异常的时候必须释放锁
 *  也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放后才能获取锁
 *  可是别的实例对象的非静态同步方法以为跟实例对象的非静态同步方法用的是不同的锁
 *  所以无需等待该实例对象以获取的非静态同步方法释放锁 就可以获得他们自己的锁
 *
 *  所有静态同步方法用的也是同一把锁-类对象本身
 *  这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的
 *  但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁
 *  而不管是同一个实例对象的静态同步方法之间
 *  还是不同实例对象的静态同步方法之间，只要他们同一个类的实例对象！
 **/
class Phone{

      public static   synchronized  void sendEmail()throws Exception{
          TimeUnit.SECONDS.sleep(4);
          System.out.println("****sendEmail;");
      }
    public   synchronized  void sendEX()throws Exception{
        System.out.println("****sendEX;");
    }
    public void sayHello()throws Exception{
        System.out.println("****sayHello");
    }
}
public class demo5 {
    public static void main(String[] args) {
      Phone phone =new Phone();
        Phone phone2 =new Phone();
      new Thread(() -> {try {
          phone.sendEmail();
      } catch (Exception e) {
          e.printStackTrace();
      }
      },"A").start();

        new Thread(() -> {try {
            phone2.sendEX();
            //phone2.sendEX();
        } catch (Exception e) {
            e.printStackTrace();
        }
        },"B").start();
    }
}
