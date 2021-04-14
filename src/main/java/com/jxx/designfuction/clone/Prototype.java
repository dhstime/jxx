package com.jxx.designfuction.clone;


public abstract class Prototype implements Cloneable{
    private String id;

    public Prototype(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public abstract Prototype clone();
}
