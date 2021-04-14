package com.jxx.designfuction.adapter.template;

/**
 *  外籍中锋
 */
public class ForeginCenter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void attack1(){
        System.out.println("外籍中锋"+name+"进攻");
    }

    public void defense1() {
        System.out.println("外籍中锋"+name+"防守");
    }
}
