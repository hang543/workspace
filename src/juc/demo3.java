package juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/21
 * @Content:
 * arraylist线程不安全
 * 1.故障现象
 * java.util.ConcurrentModificationException 并发修改异常
 * 2.导致原因
 * 多个线程对统一资源类读写同时进行操作造成数据不一致
 * 3.解决方法
 * 3.1new Vector<>();
 * 3.2Collections.synchronizedList(new ArrayList<>());
 * 3.3new CopyOnWriteArrayList<>();
 * 4.优化建议
 *
 *
 * 源码
 *  public boolean add(E e) {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             Object[] elements = getArray();
 *             int len = elements.length;
 *             Object[] newElements = Arrays.copyOf(elements, len + 1);
 *             newElements[len] = e;
 *             setArray(newElements);
 *             return true;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 **/


public class demo3 {
    public static void main(String[] args) {
        //Collections.synchronizedList(new ArrayList<>()); //new ArrayList<>();  //new Vector<>();
        //listNotSafe();
        //setNotSafe();
        mapNotSafe();
    }

    private static void mapNotSafe() {
        Map<String,String> map =new ConcurrentHashMap<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }

    private static void setNotSafe() {
        Set<String> set=new CopyOnWriteArraySet<>();
        for (int i = 0; i <=30; i++) {
            new Thread(()->{set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);},String.valueOf(i)).start();

        }
    }

    private static void listNotSafe() {
        List<String> list=new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
