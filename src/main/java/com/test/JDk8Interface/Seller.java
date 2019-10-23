package com.test.JDk8Interface;

public class Seller implements Runnable{
    private int ticket;
    public Seller(int ticket){
        this.ticket=ticket;
    }
    @Override
    public void run(){
        while (ticket>0){
            ticket--;
            System.out.println("剩余"+ticket+"张票");
        }
    }
}