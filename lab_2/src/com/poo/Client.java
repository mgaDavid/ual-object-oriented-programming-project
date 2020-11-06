package com.poo;

import java.util.ArrayList;
import java.util.Date;

public class Client {

    private String name;
    private IdDocument document;
    private Date birthday;
    private String address;
    private String email;
    private PhoneContact contact;
    private ArrayList<Account> myAccounts;

    public Client(String name, IdDocument document, Date birthday, String address, String email, PhoneContact contact){
        this.name = name;
        this.document = document;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.myAccounts = new ArrayList<>();
    }

    public Object getName(){
        return this.name;
    }

    public IdDocument getDocument(){
        return this.document;
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

    public Account getAccount(int accountId){
        Account thisAccount = myAccounts.get(0);
        for (Account account: myAccounts){
            if (account.getAccountNumber() == accountId){
                thisAccount = account;
                break;
            }
        }
        return thisAccount;
    }

    public boolean isMyAccount(int accountId){
        for (Account account: myAccounts){
            if (account.getAccountNumber() == accountId){
                return true;
            }
        }
        return false;
    }

    public boolean equals(IdDocument document){
        return document.equals(this.getDocument());
    }
}
