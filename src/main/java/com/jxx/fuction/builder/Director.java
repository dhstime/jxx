package com.jxx.fuction.builder;

/**
*   指挥者,用来根据需求构建小人对象
*/
public class Director {
    //指挥建造过程和顺序等

    public void Construct(Builder builder){
        builder.BuildPartA();
        builder.BuildPartB();
    }
}
