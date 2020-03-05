package com.jxx.fuction.status;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class ConcreteStateA extends State {
    @Override
    public void handle(Context context) {
        context.request();
        System.out.println("ConcreteStateA");
    }
}
