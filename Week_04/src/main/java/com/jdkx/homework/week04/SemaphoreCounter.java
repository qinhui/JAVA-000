package com.jdkx.homework.week04;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter {

    public static void main(String[] args){

        Thread thread_ = new Thread(new Runnable() {
            @Override
            public void run() {

                SemaphoreCounter test_ = new SemaphoreCounter();
                test_.incrAndGet();
                int i =  test_.getSum();
                System.out.print("thread_---->"+sum);

            }
        });

        Thread thread_a = new Thread(new Runnable() {
            @Override
            public void run() {

                SemaphoreCounter test_ = new SemaphoreCounter();
                test_.incrAndGet();
                int i =  test_.getSum();
                System.out.print("thread_a----->"+sum);

            }
        });

        thread_.start();
        thread_a.start();



    }

    private static int sum =0;

    private Semaphore readSemaphore_  = new Semaphore(100,true);

    private Semaphore writeSemaphore = new Semaphore(1);

    public int incrAndGet(){

        try{

//            writeSemaphore.acquireUninterruptibly();
            return ++sum;

        }
       finally {
//            writeSemaphore.release();
        }

    }

    public int getSum(){

        try{
//            readSemaphore_.acquireUninterruptibly();
            return sum;
        }finally {
//            readSemaphore_.release();
        }


    }


}
