package com.damuzee.model;

import java.util.concurrent.CountDownLatch;

public class AtomicExampleTest {
    private static MyObject obj;

    public static void setUp()
    {
        obj = new MyObject(new Book("Java 2 From Scratch"));
    }

    public static void testUpdate(Book oldVal,int i)
    {
        obj.setWhatImReading(oldVal, new Book( 
                "Pro Java EE "+i+" Performance Management and Optimization" ) );
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        final CountDownLatch counter = new CountDownLatch(1);
        setUp();

        System.out.println("before:"+obj.getWhatImReading().getName());
        
        final Book oldVal = obj.getWhatImReading();
        
        Thread[] threads = new Thread[10];
        
        for(int i = 0; i<10; i++){
            final int num = i;
            threads[i]=new Thread(new Runnable(){
                public void run() {
                    try {
                        counter.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    testUpdate(oldVal,num);
                }
            });
            threads[i].start();
        }
        
        counter.countDown();
        for(Thread t : threads){
            t.join();
        }
        System.out.println("after:"+obj.getWhatImReading().getName());
    }
}
