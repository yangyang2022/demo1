package com.read.javaThread;

import com.yangyang.util.SleepUtils;
import org.junit.Test;

public class ThreadDemo {
    @Test
    public void testThread1() {

        Runnable r = ()-> System.out.println("hello world");
        Thread t = new Thread(r);
        System.out.println(t.getState());
        //t.setName("hello Thread");
        //t.start();
        //System.out.println(t.getName());

        SleepUtils.sleep1Second();

    }

    @Test
    public void testThread2() {

        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
