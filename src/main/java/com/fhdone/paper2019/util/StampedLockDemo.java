package com.fhdone.paper2019.util;

import java.util.concurrent.locks.StampedLock;


//https://blog.csdn.net/u010512429/article/details/80314721
public class StampedLockDemo {

    private int balance;

    private StampedLock lock = new StampedLock();
    public StampedLockDemo() {
        balance=10;
    }

    public void conditionReadWrite (int value) {
        // 首先判断balance的值是否符合更新的条件
        long stamp = lock.readLock();
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if(writeStamp != 0) { // 成功转换成为写锁
                System.out.println("tryConvertToWriteLock success");
                stamp = writeStamp;
                balance += value;
                break;
            } else {
                System.out.println("tryConvertToWriteLock fail");
                // 没有转换成写锁，这里需要首先释放读锁，然后再拿到写锁
                lock.unlockRead(stamp);
                // 获取写锁
                stamp = lock.writeLock();
            }
        }
        lock.unlock(stamp);
    }

    public void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int c = balance;
        // 这里可能会出现了写操作，因此要进行判断
        if(!lock.validate(stamp)) {
            // 要重新读取
            stamp = lock.readLock();
            try{
                c = balance;
            }
            finally{
                lock.unlockRead(stamp);
            }
        }
        System.out.println("读取的值为:"+c);
    }

    public void read () {
        long stamp = lock.readLock();
        lock.tryOptimisticRead();
        int c = balance;
        System.out.println("读取的值为:"+c);
        // ...
        lock.unlockRead(stamp);
    }

    public void write(int value) {
        long stamp = lock.writeLock();
        balance += value;
        lock.unlockWrite(stamp);
    }


    public static void main(String[] args) {

        StampedLockDemo demo=new StampedLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    demo.read();
                    demo.optimisticRead();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    demo.write(2);
                    demo.conditionReadWrite(3);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
