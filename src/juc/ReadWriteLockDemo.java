package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/2/26
 * @Content:
 **/
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
                try {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
                    map.put(key, value);
                    System.out.println(Thread.currentThread().getName() + "\t 写入数据完成"+key);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readWriteLock.writeLock().unlock();
                }

    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 读取数据" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取数据完成"+o);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        readWrite(myCache);

    }

    public static void readWrite(MyCache myCache) {
        for (int i = 0; i <5 ; i++) {
            final  int i1=i;
            new Thread(()->{
                myCache.put(i1+"",i1+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <5 ; i++) {
            final  int i1=i;
            new Thread(()->{
                myCache.get(i1+"");
            },String.valueOf(i)).start();
        }
    }
}
