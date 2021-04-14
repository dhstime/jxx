package com.jxx.designfuction.observer;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class TestObserver {
    public static void main(String [] args){
        ConcreteSubject s = new ConcreteSubject();

        s.Attach(new ConcreteObserver("X",s));
        s.Attach(new ConcreteObserver("Y",s));
        s.Attach(new ConcreteObserver("Z",s));

        s.setSubjectState("ABC");
        s.Notify();
    }
}
