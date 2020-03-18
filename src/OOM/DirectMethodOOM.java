package OOM;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/28
 * @Content:
 **/
public class DirectMethodOOM {
    private static final  int  _1MB=1024*1024;
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeFiled= Unsafe.class.getDeclaredFields()[0];
        unsafeFiled.setAccessible(true);
        Unsafe unsafe= (Unsafe) unsafeFiled.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
