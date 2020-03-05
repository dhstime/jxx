package com.jxx.fuction.decorator;

public class ConcreteComponent implements Component{
    @Override
    public String write(String s) {
        s ="ConcreteComponent:具体操作对象";
        System.out.println("ConcreteComponent:具体操作对象");
        return s;
    }
}
