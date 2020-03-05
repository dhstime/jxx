package com.jxx.fuction.adapter.template;

/**
 *  翻译者
 */
public class Translator extends Player{

    private ForeginCenter foreginCenter = new ForeginCenter();

    public Translator(String name) {
        super(name);
    }

    @Override
    public void attack() {
        foreginCenter.attack1();
    }

    @Override
    public void defense() {
        foreginCenter.defense1();
    }
}
