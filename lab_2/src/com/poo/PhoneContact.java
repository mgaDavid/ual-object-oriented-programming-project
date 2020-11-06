package com.poo;

public class PhoneContact {

    private int phoneNumber;
    private String type;

    public PhoneContact(int phoneNumber, String type){
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public int getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getType(){
        return this.type;
    }

    public void setPhoneNumber(int phoneNumber){ this.phoneNumber = phoneNumber; }

    public void setType(String type){
        this.type = type;
    }
}
