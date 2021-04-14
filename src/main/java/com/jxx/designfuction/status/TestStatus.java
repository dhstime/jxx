package com.jxx.designfuction.status;

import com.jxx.designfuction.status.template.Work;

public class TestStatus {
    public static void main(String [] args){
        Work work = new Work();
        work.setHour(9);
        work.writeProgram();
        work.setHour(10);
        work.writeProgram();
        work.setHour(12);
        work.writeProgram();
        work.setHour(14);
        work.writeProgram();
        work.setHour(17);
        work.writeProgram();
        work.setHour(20);
        work.writeProgram();

        work.setFinish(true);
        work.setHour(21);
        work.writeProgram();
        work.setHour(24);
        work.writeProgram();
    }
}
