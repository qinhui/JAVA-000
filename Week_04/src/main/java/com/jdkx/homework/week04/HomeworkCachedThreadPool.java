package com.jdkx.homework.week04;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeworkCachedThreadPool {

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            Callable<Integer> callable_ = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Integer val_ = HomeworkCachedThreadPool.sum();
                    System.out.println("异步执行-->"+val_);
                    return val_;
                }
            };

            Integer result_ = executorService.submit(callable_).get();
            executorService.shutdown();

            // 异步执行 下面方法
//        int result = sum(); //这是得到的返回值


            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result_);
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
}


