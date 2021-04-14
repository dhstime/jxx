package com.jxx.designfuction.status.template;


public class Work {
    private State current;

    public Work() {
        current = new ForenoonState();
    }
    //钟点属性,状态转换的依据
    private double hour;

    //任务完成属性,是否下班的依据
    private boolean finish = false;

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void setCurrent(State current) {
        this.current = current;
    }
    public void writeProgram(){
        current.writeProgram(this);
    }
}
