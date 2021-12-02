package com.student.stu.Entity;

import java.time.Duration;
import java.time.Instant;

public class threadGenStu implements Runnable{
    private Thread thread;
    private String threadName;
    private Integer res;

    public threadGenStu(String threadName){
        this.threadName = threadName;
    }
    public void run(){
        //try{
            Instant t1 = Instant.now();
            Integer x = 0;
            for(Integer i = 0; i < 10000; i++)
                x++;
            this.res = x;
            Instant t2 = Instant.now();

            //System.out.println("res of " + threadName + ":" +  x);
            //System.out.println("time:" + Duration.between(t1, t2));
        /*}catch (InterruptedException e){
            System.out.println("thread" + threadName + e.toString());
        }*/
    }

    public Integer getRes(){
        return this.res;
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
