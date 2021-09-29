package com.ellsom.bbs.Controller.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class hellocontroller {
    static class rw extends ReentrantLock {
        private int loopnum;
        public rw(int l){
            loopnum=l;
        }
        public void print(String s, Condition c, Condition n) throws InterruptedException {
            for (int i = 0; i <loopnum ; i++) {
                lock();
                try {
                    c.await();
                    System.out.print(s);
                    n.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    unlock();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        rw lock=new rw(10);
        Condition a= lock.newCondition();
        Condition b= lock.newCondition();
        Condition c= lock.newCondition();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{});
        new Thread(()->{
            try {
                lock.print("a",a,b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                lock.print("b",b,c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                lock.print("c",c,a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        lock.lock();
        Thread.sleep(10);
        try {
            a.signal();
        }finally {
            lock.unlock();
        }

    }


}
