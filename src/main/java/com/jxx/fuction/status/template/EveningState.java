package com.jxx.fuction.status.template;

/**
 *  晚上状态
 */
public class EveningState extends State{
    @Override
    public void writeProgram(Work work) {
        if(work.isFinish()){
            work.setCurrent(new RestState());
            work.writeProgram();
        }else {
            if (work.getHour() < 21){
                System.out.println("当前时间"+work.getHour()+"加班奥利给");
            }else {
                work.setCurrent(new SleepingState());
                work.writeProgram();
            }
        }
    }
}
