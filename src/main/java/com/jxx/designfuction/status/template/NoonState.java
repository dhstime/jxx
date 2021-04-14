package com.jxx.designfuction.status.template;

/**
*   中午状态
*/
public class NoonState extends State{
    @Override
    public void writeProgram(Work work) {
        if(work.getHour() < 13){
            System.out.println("当前时间"+work.getHour()+"饿了");
        }else{
            work.setCurrent(new AfterNoonState());
            work.writeProgram();
        }
    }
}
