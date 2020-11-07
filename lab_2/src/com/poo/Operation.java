package com.poo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static java.lang.Math.abs;

/**
 * In this class we have the Operations constructor of our program
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 08/11/2020
 */

public class Operation {

    private final double amount;
    private final LocalDateTime date;
    private final String type;
    private final double tax;

    /**
     * This os the Operations constructor
     * We have the following parameters
     *
     * @param type (Type of operation - Credit of Debit)
     * @param date (Current Date and time when the operation is registered)
     * @param amount (Amount of the operation)
     * @param tax (Tax amount in the operation)
     */
    public Operation(String type, double amount, double tax) {
        if (type.equals("DÉBITO")) this.amount = -abs(amount);
        else this.amount = abs(amount);
        this.date = LocalDateTime.now();
        this.type = type;
        this.tax = tax;
    }

    /**
     * Method to return the Amount of the operation
     * @return amount (Amount of the operation)
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Method to return the current Date, Hour and minute
     * @return date (Date, Hour and minute)
     */
    public LocalDateTime getDate(){
        return this.date;
    }

    /**
     * Method to return type of Operation
     * @return type (Type of Operation - Credit or Debit)
     */
    public String getType(){
        return this.type;
    }

    /**
     * Method to return current Tax used in the operation
     * @return tax (Tax used in the operation)
     */
    public double getTax() {
        return this.tax;
    }

    /**
     * Method used return formatted print of the operation
     * @return date,type,tax,amount
     */
    @Override
    public String toString() {
        DateTimeFormatter newFormat = DateTimeFormatter
                .ofPattern("uuuu/MM/dd HH:mm:ss")
                .withResolverStyle(ResolverStyle.STRICT);

        String date = this.date.format(newFormat);
        String amount = new DecimalFormat("0.0000").format(this.getAmount());

        return "Data: " + date + " Tipo: " + this.getType() + " Taxa: " + this.getTax() + " Valor: " + amount;
    }

}
