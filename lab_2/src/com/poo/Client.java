package com.poo;

import java.text.SimpleDateFormat;
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

    public IdDocument getDocument(){
        return this.document;
    }

    public ArrayList<Account> getAccounts() { return this.myAccounts; }

    public void setName(String name){
        this.name = name;
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

    public void addAccount(Account account){ this.myAccounts.add(account); }

    public Account getAccount(int accountId){
        Account thisAccount = this.getAccounts().get(0);
        for (Account account: this.getAccounts()){
            if (account.getNumber() == accountId){
                thisAccount = account;
                break;
            }
        }
        return thisAccount;
    }

    public boolean isMyAccount(int accountId){
        for (Account account: this.getAccounts()){
            if (account.getNumber() == accountId){
                return true;
            }
        }
        return false;
    }

    public boolean equals(IdDocument document){ return document.equals(this.getDocument()); }

    public String getBirthday() {
        return new SimpleDateFormat("yyyy/MM/dd").format(this.birthday);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PhoneContact getContact() {
        return contact;
    }
}
