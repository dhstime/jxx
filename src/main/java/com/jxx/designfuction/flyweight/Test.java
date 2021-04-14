package com.jxx.designfuction.flyweight;

/**
 * @author Strange
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2021年03月09日 21:45:00
 */
public class Test {
    public static void main(String[] args) {
        // 亨元工厂
        IgoChessmanFactory igoChessmanFactory = IgoChessmanFactory.getInstance();

        // 取两颗黑子
        IgoChessman blackOne = igoChessmanFactory.getIgoChessman("b");
        IgoChessman blackTwo = igoChessmanFactory.getIgoChessman("b");
        System.out.println("黑子是否相同：" + (blackOne == blackTwo));

        // 取两颗白子
        IgoChessman whiteOne = igoChessmanFactory.getIgoChessman("w");
        IgoChessman whiteTwo = igoChessmanFactory.getIgoChessman("w");
        System.out.println("白子是否相同：" + (whiteOne == whiteTwo));

        System.out.println("\n");

        // 显示棋子，同时设置棋子的坐标位置
        blackOne.display(new Coordinates(1, 2));
        blackTwo.display(new Coordinates(2, 3));
        whiteOne.display(new Coordinates(1, 3));
        whiteTwo.display(new Coordinates(2, 2));
    }
}
