package com.jxx.designfuction.proxy;

public class Pursuit implements GiveGift{

    Person person;

    public Pursuit(Person person) {
        this.person = person;
    }

    @Override
    public void give() {
        System.out.println("Pursuit,"+person.getName());
    }
}
