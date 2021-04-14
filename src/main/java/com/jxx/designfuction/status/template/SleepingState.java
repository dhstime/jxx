package com.jxx.designfuction.status.template;

/**
 *  睡觉状态
 */
public class SleepingState extends State{
    @Override
    public void writeProgram(Work work) {
        System.out.println("当前时间"+work.getHour()+"睡觉了");
    }
}
