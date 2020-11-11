package com.jdkx.homework.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeworkSingleThreadExecutor {


    public static void  main(String[] args){

        long start=System.currentTimeMillis();
        Integer result = 0;

        try {

            ExecutorService executorService_ = Executors.newSingleThreadExecutor();

            Callable<Integer> callable_ = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int temp = HomeworkSingleThreadExecutor.sum();
                    System.out.println("callable_---->"+temp);
                    return temp;
                }
            };

            result = executorService_.submit(callable_).get();

            executorService_.shutdown();


        }catch (Exception e){
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

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
