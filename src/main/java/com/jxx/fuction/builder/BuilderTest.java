package com.jxx.fuction.builder;

public class BuilderTest {
    public static void main(String [] args){
        Director director = new Director();
        Builder b1 = new ConcreteBuilder1();
        Builder b2 = new ConcreteBuilder2();

        director.Construct(b1);
        Product p1 = b1.GetResut();
        p1.show();
        director.Construct(b2);
        Product p2 = b2.GetResut();
        p2.show();
    }
}
