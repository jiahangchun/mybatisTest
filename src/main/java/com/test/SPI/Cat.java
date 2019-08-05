package com.test.SPI;

public class Cat implements IShout {

    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}