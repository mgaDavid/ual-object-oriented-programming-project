package com.poo;

/**
 * In this class we have the Phone Contact constructor of our program
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 08/11/2020
 */
public class PhoneContact {

    private int number;
    private String type;

    /**
     * This is the PhoneContact constructor
     * @param number (Phone number of Client)
     * @param type (Type of Contact - Cell, house, work, etc...)
     */
    public PhoneContact(int number, String type) {
        this.number = number;
        this.type = type;
    }

    /**
     * This method returns the Phone number
     * @return number (Phone Number)
     */
    public int getNumber() {
        return number;
    }

    /**
     * This method sets the Phone Number of client
     * @param number (Phone number)
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * This method returns type of contact information in the Phone Contact object
     * @return type (type of contact information)
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets the type of contact in the Phone Contact object
     * @param type (type of contact)
     */
    public void setType(String type) {
        this.type = type;
    }
}
