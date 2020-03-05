package com.jxx.fuction.clone.Test;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class Resume implements Cloneable{

    private String name;
    private String id;

    private WorkExperience workExperience;

    public Resume(String name) {
        this.name = name;
    }

    public Resume(String name, String id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Resume resume = (Resume)super.clone();
        workExperience = ((WorkExperience) workExperience.clone());
        return resume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display(){
        System.out.println("name:"+name+"id:"+id);
        System.out.println(workExperience.toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkExperience getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(WorkExperience workExperience) {
        this.workExperience = workExperience;
    }
}
