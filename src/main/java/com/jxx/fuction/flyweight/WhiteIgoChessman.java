package com.jxx.fuction.flyweight;

/**
 * @author Strange
 * @ClassName WhiteIgoChessman.java
 * @Description TODO 具体亨元类: 内部状态类（共享） 白色棋子
 * @createTime 2021年03月09日 21:41:00
 */
public class WhiteIgoChessman implements IgoChessman{
    @Override
    public String getColor() {
        return "白色";
    }
}
