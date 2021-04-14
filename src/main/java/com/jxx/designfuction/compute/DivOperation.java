package com.jxx.designfuction.compute;

public class DivOperation extends Operation{
    @Override
    public Double getresult() {
        Double result;
        if(b.equals(0)){
            return null;
        }
        result= a/b;
        return result;
    }
}
