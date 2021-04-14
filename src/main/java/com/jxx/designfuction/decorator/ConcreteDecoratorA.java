package com.jxx.designfuction.decorator;

public class ConcreteDecoratorA extends Decorator {
    //ConcreteDecoratorA特有属性用于区别ConcreteDecoratorB
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String write(String s) {
        super.write(s);
        System.out.println("ConcreteDecoratorA:装饰者A特有属性:"+str);
        System.out.println("ConcreteDecoratorA:装饰者A");
        return "ConcreteDecoratorA:装饰者A";
    }
}
