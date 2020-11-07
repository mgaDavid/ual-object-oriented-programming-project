package com.poo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static java.lang.Math.abs;
import java.math.RoundingMode;

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

    private final BigDecimal amount;
    private final LocalDateTime date;
    private final String type;
    private final BigDecimal tax;

    /**
     * This os the Operations constructor
     * We have the following parameters
     *
     * @param type (Type of operation - Credit of Debit)
     * @param amount (Amount of the operation)
     * @param tax (Tax amount in the operation)
     */
    public Operation(String type, double amount, BigDecimal tax) {
        if (type.equals("DÉBITO")) this.amount = new BigDecimal(-abs(amount)).setScale(4, RoundingMode.HALF_UP);
        else this.amount = new BigDecimal(abs(amount)).setScale(4, RoundingMode.HALF_UP);
        this.date = LocalDateTime.now();
        this.type = type;
        this.tax = tax.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Method to return the Amount of the operation
     * @return amount (Amount of the operation)
     */
    public BigDecimal getAmount(){
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
    public BigDecimal getTax() {
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

        return "Data: " + date + " Tipo: " + this.getType() + " Taxa: " + this.getTax() + " Valor: " + this.getAmount();
    }

}
