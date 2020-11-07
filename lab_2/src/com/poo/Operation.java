package com.poo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

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
        DateTimeFormatter newFormat = DateTimeFormatter
                .ofPattern("uuuu-MM-dd HH:mm:ss")
                .withResolverStyle(ResolverStyle.STRICT);

        String date = this.date.format(newFormat);

        //String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(this.date);
        String amount = new DecimalFormat("0.0000").format(this.getAmount());

        return "Data: " + date + " Tipo: " + this.getType() + " Taxa: " + this.getTax() + " Valor: " + amount;
    }

}
