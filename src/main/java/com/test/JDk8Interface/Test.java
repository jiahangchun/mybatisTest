package com.test.JDk8Interface;

import com.alipay.sofa.rpc.common.utils.JSONUtils;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Test {
//    public static void main(String[] args) {
////
////        TestInterface t=()->{
////            System.out.println("sssss");
////        };
////        t.sub();
////        t.defaultMethod();
//
//        List<Long> idList= Lists.newArrayList(1L,2L,3L,4L,5L,6L);
//        List<Long>  expo=idList.stream().filter(x->{
//            return x%2==0;
//        }).collect(Collectors.toList());
//        idList.removeAll(expo);
//        System.out.println("aaa");
//    }


    public static void main(String[] args) {
        List<Long> json=new ArrayList<>();
        json.add(505145L);
        json.add(506180L);
        System.out.println(JSONUtils.toJSONString(json));
    }


    public void testDistributedLock(Integer type) {
        final Integer[] a = {0};
        List<Runnable> list = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            Runnable r = () -> {
                try {
                    Thread.sleep((long) Math.random());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (type) {
                    case 1:
                        b(a);
                        break;
                    case 2:
                        c(a);
                        break;
                }

            };
            list.add(r);
        }
        list.forEach(x -> {
            new Thread(x).start();
        });
    }

    public int b(Integer[] a) {
        for (int i = 0; i < 10; i++) {
            a[0] = a[0] + 1;
            System.out.println(Thread.currentThread().getName() + "  没任何修饰 ： " + a[0]);
        }
        return a[0];
    }

    public int c(Integer[] a) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                a[0] = a[0] + 1;
                System.out.println(Thread.currentThread().getName() + "  synchronized ： " + a[0]);
            }
        }
        return a[0];
    }


}
