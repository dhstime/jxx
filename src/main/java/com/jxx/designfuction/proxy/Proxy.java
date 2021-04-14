package com.jxx.designfuction.proxy;

public class Proxy implements GiveGift {

    Pursuit pursuit;


    public Proxy( Person person) {
        pursuit = new Pursuit(person);
    }

    @Override
    public void give() {
        System.out.println("代理类追求");
        pursuit.give();
    }
}
