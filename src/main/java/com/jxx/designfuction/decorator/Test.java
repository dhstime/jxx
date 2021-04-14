package com.jxx.designfuction.decorator;

public class Test {
    public static void main(String [] args){
        ConcreteComponent concreteComponent = new ConcreteComponent();
        Decorator decorator = new Decorator();
        ConcreteDecoratorA a = new ConcreteDecoratorA();
        a.setStr("AA特有");
        a.setComponent(concreteComponent);
        ConcreteDecoratorB b = new ConcreteDecoratorB();
        b.setComponent(a);


        concreteComponent.write("concreteComponent");
        System.out.println("---------------");
        a.write("a");
        System.out.println("---------------");
        b.write("b");

    }
}
