package com.poo;

import java.util.Date;

public class Operation {

    private double amount;
    private Date date;
    private String operationType;

    public Operation(double amount, Date date, String operationType){
        this.amount = amount;
        this.date = date;
        this.operationType = operationType;
    }

    public Object getAmount(){
        return this.amount;
    }

    public Object getDate(){
        return this.date;
    }

    public String getOperationType(){
        return this.operationType;
    }

}
