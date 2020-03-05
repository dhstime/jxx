package com.jxx.fuction.builder;

/**
*  具体建造者,实现Builder接口,构建和装配各个部件
*/
public class ConcreteBuilder1 extends Builder{
    //具体建造类,确定具体组成部分

    private Product product = new Product();

    @Override
    public void BuildPartA() {
        product.add("1A");
    }

    @Override
    public void BuildPartB() {
        product.add("1B");
    }

    @Override
    public Product GetResut() {
        return product;
    }
}
