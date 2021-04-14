package com.jxx.designfuction.observer;

/**
*   抽象观察者,为所有具体观察者定义一个借口,在得到主题通知时更新自己
*/
public abstract class Observer {

    public abstract void Update();
}
