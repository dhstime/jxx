package com.jxx.designfuction.proxy;

public class Test {
    public static void main(String [] args){
        Person person = new Person();
        person.setName("MM");
        Proxy proxy = new Proxy(person);
        proxy.give();

    }
}
