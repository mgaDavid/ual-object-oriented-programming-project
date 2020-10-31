package com.poo;

public class phoneContact {

    int phoneNumber;
    String type;

    public phoneContact(int phoneNumber, String type){
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public Object getPhoneNumber(){
        return this.phoneNumber;
    }

    public Object getType(){
        return this.type;
    }

    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setType(String type){
        this.type = type;
    }
}
