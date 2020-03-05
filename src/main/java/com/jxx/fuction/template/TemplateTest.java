package com.jxx.fuction.template;

public class TemplateTest {
    public static void main(String [] args){
        AbstractClass a ;

        a = new ConcreteClassA();
        a.TemplateMethod();

        a = new ConcreteClassB();
        a.TemplateMethod();
    }
}
