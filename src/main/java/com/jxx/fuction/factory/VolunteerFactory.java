package com.jxx.fuction.factory;

/**
 *志愿者工厂
 * @author strange
 * @date $
 */
public class VolunteerFactory implements IFactory{
    @Override
    public LeiFeng CreateLeiFeng() {
        return new Volunteer();
    }
}
