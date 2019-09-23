package com.sofa.server;

import java.math.BigDecimal;

/**
 * Quick Start byteBufDemo implement
 * @author jiahangchun
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }

    public static void main(String[] args) {
        BigDecimal a=null;
        if(a.equals(BigDecimal.ZERO)){
            System.out.println("aaa");
        }else{
            System.out.println("nnbb");
        }
    }

}