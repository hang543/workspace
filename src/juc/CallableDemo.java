package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/22
 * @Content:
 * java 多线程的第三种获取方式
 * 1. get方法一般请放在最后一行
 * 2.同样的方法只会执行一次
 **/


class  MyThred implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("********come call method");
        System.out.println("最新的更新");
        return 1024;
    }
}
public class CallableDemo  {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask=new FutureTask(new MyThred());
        new Thread(futureTask,"A").start();
        Integer result=futureTask.get();
        System.out.println(result);
    }
}
