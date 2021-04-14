package com.jxx.designfuction.observer;

/**
 *  具体观察者,实现抽象观察者所要求的的更新接口,
 *  以便使本身的状态与主题的状态相互协调.具体观察者角色可以保存一个指向主题对象的
 *  引用.具体观察者角色通常用一个具体子类实现
 */
public class ConcreteObserver extends Observer{
    private String name;
    private String observerState;
    private ConcreteSubject concreteSubject;

    public ConcreteObserver(String name, ConcreteSubject concreteSubject) {
        this.name = name;
        this.concreteSubject = concreteSubject;
    }

    @Override
    public void Update() {
       System.out.println(name+"观察者的更新新状态"+concreteSubject.getSubjectState());
    }

    public String getObserverState() {
        return observerState;
    }

    public void setObserverState(String observerState) {
        this.observerState = observerState;
    }
}

