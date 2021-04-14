package com.jxx.designfuction.factory;

/**
 *简单工厂
 * @author strange
 * @date $
 */
public class SimpleFactory {

    public static LeiFeng createrLeiFeng(String type){
        LeiFeng result =null;
        switch (type){
            case "da":
                result = new Undergraduate();
                break;
            case "zhi":
                result = new Volunteer();
                break;
        }
        return result;
    }
}
