package com.jxx.designfuction.adapter;

public class Adapter extends Target{

    private Adaptee adaptee = new Adaptee();

    public void request(){
        adaptee.specificRequest();
    }

}
