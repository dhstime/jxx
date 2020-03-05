package com.jxx.fuction.decorator;

public class Decorator implements Component{

    private Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public String write(String s) {
        if(component!=null) {
            String write = component.write(s);
            System.out.println("Decorator:装饰者");
            return write+":Decorator:装饰者";
        }
        return null;
    }
}
