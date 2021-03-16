package com.jxx.fuction.flyweight;

/**
 * @author Strange
 * @ClassName IgoChessmanFactory.java
 * @Description TODO
 * @createTime 2021年03月09日 21:42:00
 */

import java.util.concurrent.ConcurrentHashMap;

/**
 * 亨元工厂类(简单工厂)，使用单例模式设计
 */
class IgoChessmanFactory {
    // 单例模式设计
    private static IgoChessmanFactory igoChessmanFactory = new IgoChessmanFactory();

    // 使用ConcurrentHashMap 来存储亨元对象
    private static ConcurrentHashMap<String, IgoChessman> chessMap;

    // 构造方法私有化，单例模式设计
    private IgoChessmanFactory() {
        chessMap = new ConcurrentHashMap<String, IgoChessman>();
        chessMap.put("b", new BlackIgoChessman());
        chessMap.put("w", new WhiteIgoChessman());
    }

    // 返回亨元工厂类的唯一实例
    public static IgoChessmanFactory getInstance() {
        return igoChessmanFactory;
    }

    // 通过key来获取在map中的亨元对象
    public IgoChessman getIgoChessman(String color) {
        return IgoChessmanFactory.chessMap.get(color);
    }
}
