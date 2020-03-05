package com.jxx.fuction.observer;

import java.util.ArrayList;
import java.util.List;

/**
*   抽象通知者
*/
public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    //增加
    public void Attach(Observer observer){
        observers.add(observer);
    }

    public void Detach(Observer observer) {
        observers.remove(observer);
    }

    //通知
    public void Notify(){
        for (Observer observer : observers) {
            observer.Update();
        }
    }

}
