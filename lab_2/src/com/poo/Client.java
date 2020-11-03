package com.poo;

import java.util.ArrayList;
import java.util.Date;

public class Client {

    private String name;
    private idDocument idNumber;
    private Date birthday;
    private String address;
    private String email;
    private PhoneContact contact;
    private ArrayList<Account> myAccounts;

    public Client(String name, idDocument idNumber, Date birthday, String address, String email, PhoneContact contact){
        this.name = name;
        this.idNumber = idNumber;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.myAccounts = new ArrayList<>();
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

    public PhoneContact getContact(){
        return this.contact;
    }

    public ArrayList<Account> getAccounts() { return this.myAccounts; }

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

    public void setContact(PhoneContact contact){
        this.contact = contact;
    }

    public void addAccount(Account account){ myAccounts.add(account); }

}
