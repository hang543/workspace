package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/27
 * @Content:
 * ForkJoinpool
 * ForkJoinTask
 * RecursiveTask
 **/
class MyTask extends RecursiveTask<Integer>{
   private  static  final  Integer ADJUST_VALUE=10;
   private  int begin;
   private int end;
   private  int  result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if ((end-begin<=ADJUST_VALUE)) {

            for (int i = begin; i <=end ; i++) {
              result+=i;
            }
        }else {
            int middle=(begin+end)/2;
            MyTask myTask0=new MyTask(begin,middle);
            MyTask myTask1 = new MyTask(middle + 1, end);
            myTask0.fork();
            myTask1.fork();
            result=myTask0.join()+myTask1.join();
        }
        return result;
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask=new MyTask(0,10);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(myTask);
        System.out.println(task.get());
        forkJoinPool.shutdown();


    }
}
