package com.jxx.designfuction.builder;

public class ConcreteBuilder2 extends Builder{
    private Product product = new Product();

    @Override
    public void BuildPartA() {
        product.add("2A");
    }

    @Override
    public void BuildPartB() {
        product.add("2B");
    }

    @Override
    public Product GetResut() {
        return product;
    }
}
