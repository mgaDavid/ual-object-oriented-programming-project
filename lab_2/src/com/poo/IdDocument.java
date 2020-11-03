package com.poo;

public class IdDocument {

    private String number;
    private String type;

    public IdDocument(String number, String type){
        this.number = number;
        this.type = type;
    }

    public Object getNumber(){
        return this.number;
    }

    public Object getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
}
