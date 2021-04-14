package com.jxx.designfuction.flyweight;

/**
 * @author Strange
 * @ClassName BlackIgoChessman.java
 * @Description TODO 具体亨元类：内部状态类（共享） 黑色棋子
 * @createTime 2021年03月09日 21:41:00
 */
public class BlackIgoChessman implements IgoChessman{
    @Override
    public String getColor() {
        return "黑";
    }
}
