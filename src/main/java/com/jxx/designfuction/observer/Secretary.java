package com.jxx.designfuction.observer;

//秘书类
public class Secretary extends Subject {
    //动作
    private String action;

    //前台状态
    public String SecretaryAction(){
        this.action = action;
        return action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
