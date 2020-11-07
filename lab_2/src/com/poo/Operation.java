package com.poo;

import java.time.LocalDateTime;

import static java.lang.Math.abs;

public class Operation {

    private final double amount;
    private final LocalDateTime date;
    private final String type;
    private final double tax;

    public Operation(String type, double amount, double tax){
        if (type.equals("DÉBITO")) this.amount = -abs(amount);
        else this.amount = abs(amount);
        this.date = LocalDateTime.now();
        this.type = type;
        this.tax = tax;
    }

    public double getAmount(){
        return this.amount;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public String getType(){
        return this.type;
    }

    public double getTax() {
        return this.tax;
    }

    @Override
    public String toString(){
        return "Data: " + this.getDate().toString() + " Tipo: " + this.getType() + " Taxa: " + this.getTax() + " Valor: " + this.getAmount();
    }

}
