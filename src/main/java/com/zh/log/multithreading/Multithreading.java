package com.zh.log.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>
 * Multithreading类
 * </p>
 *
 * 线程（最小执行单位）
 * 线程是操作系统能够进行运算调度的最小单位。它被包含在进程之中，是进程中的实际运作单位。
 *
 * 进程（资源分配的最小单位）
 *  程序运行起来的实例，进程，也叫任务。就是正常执行的程序。
 *
 * 并发
 * 并发:在同一时刻，有多个指令在单个CPU上交替执行
 *
 * 并行
 * 并行:在同一时刻，有多个指令在多个CPU上同时执行
 *
 * @author zh
 * @date 2025-06-25 11:32
 */
public class Multithreading {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //// 创建第一个线程
        //PrimeThread thread1 = new PrimeThread();
        //// 创建第二个线程
        //PrimeThread thread2 = new PrimeThread();
        //// 方便 识别 给线程取个别名
        //thread1.setName("线程1");
        //thread2.setName("线程2");
        //
        //// 执行线程
        //thread1.start();
        //thread2.start();

        //// 创建PrimeRunnable对象
        //PrimeRunnable runnable = new PrimeRunnable();
        //// 创建线程对象,并且执行PrimeRunnable对象
        //Thread t1 = new Thread(runnable);
        //Thread t2 = new Thread(runnable);
        //t1.setName("线程1");
        //t2.setName("线程2");
        //// 开启线程
        //t1.start();
        //t2.start();

        ////创建MyCallable的对象(表示多线程要执行的任务)
        //PrimeCallable mc =new PrimeCallable();
        ////创建FutureTask的对象(作用管理多线程运行的结果)
        // FutureTask<Integer> ft=new FutureTask<>(mc);
        // //创建线程的对象
        //Thread t1 = new Thread(ft);
        ////启动线程
        // t1.start();
        ////获取多线程运行的结果
        //Integer result =ft.get();
        //
        //System.out.println(result);


        // 创建第一个线程
        synThread thread1 = new synThread();
        // 创建第二个线程
        synThread thread2 = new synThread();
        // 创建第三个线程
        synThread thread3 = new synThread();
        // 方便 识别 给线程取个别名
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread3.setName("线程3");

        // 执行线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

// 多线程实现1
class PrimeThread extends Thread{
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + ": 你好！");
        }
    }
}

class PrimeRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": 你好！");
        }
    }
}

// 多线程实现方式3
//特点:可以获取到多线程运行的结果
class PrimeCallable implements Callable {
    @Override
    public Integer call() throws Exception {
        // 求1-100之和
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum+=i;
        }
        return sum;
    }
}

// 线程安全【同步锁】
class synThread extends Thread{
    static int tickt = 0;
    public void run(){
        while(true){
            synchronized (this){
                if(tickt<100){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    tickt++;
                    System.out.println(getName()+"在卖第"+tickt+"张票！");
                }else {
                    break;
                }
            }
        }
    }

}

