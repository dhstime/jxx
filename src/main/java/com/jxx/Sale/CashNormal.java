package com.jxx.Sale;

public class CashNormal extends Discount{
    @Override
    public Double accpectCash(double money) {
        return money;
    }
}
