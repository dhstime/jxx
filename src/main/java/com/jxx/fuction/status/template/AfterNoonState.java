package com.jxx.fuction.status.template;

/**
 *  下午状态
 */
public class AfterNoonState extends State{
    @Override
    public void writeProgram(Work work) {
        if(work.getHour() < 17){
            System.out.println("当前时间"+work.getHour()+"下午干活奥利给");
        }else{
            work.setCurrent(new EveningState());
            work.writeProgram();
        }
    }
}
