package com.jdkx.homework.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
public  class HomeworkFutureTask implements Callable<Integer> {




    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

//        int result = sum(); //这是得到的返回值

        FutureTask<Integer> ft = new FutureTask<>(new HomeworkFutureTask());

        new Thread(ft).start();

        // 确保  拿到result 并输出
        try {
            System.out.println("异步计算结果为：" + ft.get());
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }


    @Override
    public Integer call() throws Exception {
        Integer val_ = HomeworkFutureTask.sum();
        System.out.println(" call -sum ===>"+val_);
        return val_;
    }
}