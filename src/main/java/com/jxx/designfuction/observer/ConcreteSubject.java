package com.jxx.designfuction.observer;

/**
*   具体主题,将有关状态存入具体观察者对象,在具体对象发生变化时通知登记过的观察者
 *
*/
public class ConcreteSubject extends Subject{
    //具体被观察者状态
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
}
