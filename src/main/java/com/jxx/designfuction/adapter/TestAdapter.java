package com.jxx.designfuction.adapter;

public class TestAdapter {
    public static void main(String [] args){
        Target target = new Adapter();
        ((Adapter) target).request();
    }
}
