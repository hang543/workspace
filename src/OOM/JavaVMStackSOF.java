package OOM;

import java.util.concurrent.*;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/28
 * @Content: 栈和本地方法栈
 **/
public class JavaVMStackSOF {
//
    private void dontStop(){
        while (true){}
    }
    public void stackLeakByThread(){
        while(true){
            Thread thread=new Thread(()->dontStop());
            thread.start();
        }
    }

    public static void main(String[] args) {
//        JavaVMStackSOF oom=new JavaVMStackSOF();
//        try {
//            oom.stackLength();
//        } catch (Exception e) {
//            System.out.println("stack length"+oom.stackLength);
//            e.printStackTrace();
//        }
//        ExecutorService threadTppl=new ThreadPoolExecutor(2,5,3L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        JavaVMStackSOF javaVMStackSOF=new JavaVMStackSOF();
        javaVMStackSOF.stackLeakByThread();
    }
}
