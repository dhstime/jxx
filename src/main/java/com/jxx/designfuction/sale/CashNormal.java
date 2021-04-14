package com.jxx.designfuction.sale;

public class CashNormal extends Discount{
    @Override
    public Double accpectCash(double money) {
        return money;
    }
}
