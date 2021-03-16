package com.jxx.fuction.flyweight;

/**
 * @author Strange
 * @ClassName IgoChessman.java
 * @Description TODO 抽象亨元接口
 * @createTime 2021年03月09日 21:40:00
 */
public interface IgoChessman {
    public abstract String getColor();

    public default void display(Coordinates coordinates) {
        System.out.println("棋子颜色：" + this.getColor() + ",棋子位置：" + coordinates);
    }
}
