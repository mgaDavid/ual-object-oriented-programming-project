package com.poo;

import java.util.ArrayList;

public class Account {

    //accountNumber provavelmente vai ser um contador e tem de ser unico.
    int accountNumber;
    Client mainClient;
    ArrayList<Client> otherClients;
    double balance;
    boolean overdraft;

    public Account(int accountNumber, Client mainClient, ArrayList<Client> otherClients, double balance, boolean overdraft){
        this.accountNumber = accountNumber;
        this.mainClient = mainClient;
        this.otherClients = otherClients;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    public Account(int accountNumber, Client mainClient, ){
        this.accountNumber = accountNumber;
        this.mainClient = mainClient;
        this.otherClients = new ArrayList<>();
        this.balance = 0;
        this.overdraft = false;
    }

    public Object getAccountNumber(){
        return this.accountNumber;
    }

    public Object getMainClient(){
        return mainClient;
    }

    public Object getOtherClients(){
        return otherClients;
    }

    public Object getBalance(){
        return balance;
    }

    public Object getOverdraft(){
        return overdraft;
    }

    public void setMainClient(Client mainClient){
        this.mainClient = mainClient;
    }

    public void setOtherClients(ArrayList<Client> otherClients){
        this.otherClients = otherClients;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setOverdraft(boolean overdraft){
        this.overdraft = overdraft;
    }
}
