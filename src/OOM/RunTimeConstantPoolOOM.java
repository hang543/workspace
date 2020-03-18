package OOM;

import sun.misc.PostVMInitHook;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/28
 * @Content:
 **/
public class RunTimeConstantPoolOOM {
    public static void main(String[] args) {
        String t1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(t1.intern()==t1);
        String t2=new StringBuilder("jia").append("va").toString();
        System.out.println(t2.intern()==t2);
    }
}
