package com.poo;

import java.util.Date;

public class Client {

    String name;
    idDocument idNumber;
    Date birthday;
    String address;
    String email;
    phoneContact contact;


    public Client(String name, idDocument idNumber, Date birthday, String address, String email, phoneContact contact){
        this.name = name;
        this.idNumber = idNumber;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.contact = contact;
    }

    public Object getName(){
        return this.name;
    }

    public idDocument getIdNumber(){
        return this.idNumber;
    }

    public Object getBirthday(){ return this.birthday; }

    public Object getAddress(){
        return this.address;
    }

    public Object getEmail(){
        return this.email;
    }

    public phoneContact getContact(){
        return this.contact;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setContact(phoneContact contact){
        this.contact = contact;
    }


}
