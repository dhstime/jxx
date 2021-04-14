package com.jxx.designfuction.sale;

import com.jxx.designfuction.compute.Operation;
import com.jxx.designfuction.compute.OperationFactory;

public class CashRebate extends Discount{
    private double moneyRebate =1d;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public Double accpectCash(double money) {
        OperationFactory op = new OperationFactory();
        Operation operation = op.CreateOperation("/");
        operation.setA(money);
        operation.setB(moneyRebate);
        return operation.getresult();
    }
}
