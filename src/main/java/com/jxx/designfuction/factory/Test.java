package com.jxx.designfuction.factory;

/**
 *
 * @author strange
 * @date $
 */
public class Test {
    public static void main(String [] args){
        IFactory factory = new UndergraduateFactory();
        LeiFeng student = factory.CreateLeiFeng();
        IFactory factory2 = new VolunteerFactory();
        LeiFeng Volunteer = factory2.CreateLeiFeng();

        LeiFeng da = SimpleFactory.createrLeiFeng("da");
        LeiFeng zhi = SimpleFactory.createrLeiFeng("zhi");

    }
}
