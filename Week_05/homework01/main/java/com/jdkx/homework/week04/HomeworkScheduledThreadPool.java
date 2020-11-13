package com.jdkx.homework.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class HomeworkScheduledThreadPool {

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(8);
        try {
            Integer result_ = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Integer val_ = HomeworkScheduledThreadPool.sum();
                    System.out.println(" executorService --->"+val_);
                    return val_;
                }
            }).get();

            executorService.shutdown();

        // 异步执行 下面方法
//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result_);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
