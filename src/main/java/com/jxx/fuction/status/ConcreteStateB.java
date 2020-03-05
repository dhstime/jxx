package com.jxx.fuction.status;

public class ConcreteStateB extends State{
    @Override
    public void handle(Context context) {
        context.request();
        System.out.println("ConcreteStateB");
    }
}
