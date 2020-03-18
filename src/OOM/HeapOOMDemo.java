package OOM;

import java.util.ArrayList;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/28
 * @Content: 堆内存溢出测试
 **/
public class HeapOOMDemo {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        while(true){
            objects.add(new Object());
        }
    }
}
