package com.jxx.fuction.status.template;

/**
 * 下班状态
 *
 */
public class RestState extends State{
    @Override
    public void writeProgram(Work work) {
        System.out.println("当前时间"+work.getHour()+"下班了啊");
    }
}
