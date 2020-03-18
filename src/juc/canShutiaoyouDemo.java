package juc;

import java.util.Random;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/23
 * @Content:
 **/
public class canShutiaoyouDemo {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());

//        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
//        long totalMemory =Runtime.getRuntime().totalMemory();//返回java虚拟机中的内存容量
//        System.out.println("Xmx:-MAX_MEMORY="+maxMemory+"字节"+(maxMemory/(double)1024/1024)+"MB");
//        System.out.println("Xms:totalMemory="+totalMemory+"字节"+(totalMemory/(double)1024/1024+"MB"));

        String str="hang";
        while (true){
            str+= str+new Random().nextInt(888888888)+new Random().nextInt(99999999);
        }
//          byte[] bytes=new byte[4*1024*1024];

    }
}
