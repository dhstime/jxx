package com.jxx.designfuction.flyweight;

/**
 * @author Strange
 * @ClassName Coordinates.java
 * @Description TODO
 * @createTime 2021年03月09日 21:42:00
 */
public class Coordinates {
    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    private Integer x;
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
