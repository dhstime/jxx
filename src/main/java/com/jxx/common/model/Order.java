package com.jxx.common.model;

/**
 * @author Strange
 * @ClassName Order.java
 * @Description TODO
 * @createTime 2020年08月06日 15:24:00
 */
public class Order {

    private  Integer id;

    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}