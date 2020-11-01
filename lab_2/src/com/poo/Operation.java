package com.poo;

import java.util.Date;

public class Operation {

    Account account;
    double amount;
    final double tax = 0.42;
    Date date;
    operationType operationType;

    public Operation(Account account, double amount, Date date, operationType operationType){
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.operationType = operationType;
    }

    public Account getAccount(){
        return this.account;
    }

    public Object getAmount(){
        return this.amount;
    }

    public Object getDate(){
        return this.date;
    }

    public operationType getOperationType(){
        return this.operationType;
    }

    //penso que nao seja necessario haver um setAccount para mudar a conta associada ao movimento, nao acho que faça sentido.

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setOperationType(operationType operationType){
        this.operationType = operationType;
    }

}
