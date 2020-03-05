package com.jxx.fuction.decorator;

public class ConcreteDecoratorB extends Decorator{



    @Override
    public String write(String s) {
        super.write(s);
        System.out.println("ConcreteDecoratorB:装饰者B");
        return "ConcreteDecoratorB:装饰者B";
    }
}
