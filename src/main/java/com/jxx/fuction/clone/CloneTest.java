package com.jxx.fuction.clone;

import com.jxx.fuction.clone.Test.Resume;
import com.jxx.fuction.clone.Test.WorkExperience;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class CloneTest {
    public static void main(String [] args) throws CloneNotSupportedException {
        Resume a = new Resume("dan ","1");
        WorkExperience a1 = new WorkExperience("120");
        a.setWorkExperience(a1);
        a.display();
        Resume b = (Resume) a.clone();
        b.setName("小 ");
        WorkExperience workExperience = b.getWorkExperience();
        workExperience.setWorkDate("300");
        b.display();
    }
}
