package com.jxx.fuction.factory;

/**
 *大学生工厂
 * @author strange
 * @date $
 */
public class UndergraduateFactory implements IFactory{
    @Override
    public LeiFeng CreateLeiFeng() {
        return new Undergraduate();
    }
}
