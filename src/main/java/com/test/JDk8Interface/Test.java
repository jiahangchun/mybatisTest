package com.test.JDk8Interface;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
//
//        TestInterface t=()->{
//            System.out.println("sssss");
//        };
//        t.sub();
//        t.defaultMethod();

        List<Long> idList= Lists.newArrayList(1L,2L,3L,4L,5L,6L);
        List<Long>  expo=idList.stream().filter(x->{
            return x%2==0;
        }).collect(Collectors.toList());
        idList.removeAll(expo);
        System.out.println("aaa");
    }
}
