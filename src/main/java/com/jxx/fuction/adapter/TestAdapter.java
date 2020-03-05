package com.jxx.fuction.adapter;

public class TestAdapter {
    public static void main(String [] args){
        Target target = new Adapter();
        ((Adapter) target).request();
    }
}
