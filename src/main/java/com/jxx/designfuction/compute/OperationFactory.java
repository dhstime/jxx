package com.jxx.designfuction.compute;

public class OperationFactory {
    public Operation CreateOperation(String oper){
        Operation operation = new Operation();
        switch (oper){
            case "+":
                operation= new AddOperation();
                break;
            case "-":
                operation=new SubOpeation();
                break;
            case "*":
                operation=new MulOperation();
                break;
            case "/":
                operation=new DivOperation();
        }
        return  operation;
    }
}
