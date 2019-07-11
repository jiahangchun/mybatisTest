package com.sofa.server;

/**
 * Quick Start demo implement
 * @author jiahangchun
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}