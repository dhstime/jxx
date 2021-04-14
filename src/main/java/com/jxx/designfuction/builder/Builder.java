package com.jxx.designfuction.builder;
/**
*  建造各部分的抽象类
 *  创建一个Product对象的各个部件指定的抽象接口
*/
public abstract class Builder {
    //确定产品类的组成部分

    public abstract void BuildPartA();
    public abstract void BuildPartB();
    public abstract Product GetResut();

}
