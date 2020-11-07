package com.poo;

import java.util.ArrayList;


public class Account {

    private final int number;
    private Client client;
    private ArrayList<Client> dependents;
    private ArrayList<Operation> operations;
    private double balance;
    private boolean overdraft;

    public Account(int number, Client client, double initialDeposit, boolean overdraft) {
        this.number = number;
        this.client = client;
        this.dependents = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.balance = 0;
        this.overdraft = overdraft;
        if (initialDeposit > 0){
            this.registerOperation(new Operation("CRÉDITO", initialDeposit, 0));
        }
    }

    public int getNumber(){ return this.number; }

    public ArrayList<Client> getDependents(){
        return dependents;
    }

    public double getBalance(){
        return this.balance;
    }

    public boolean getOverdraft(){
        return overdraft;
    }

    public ArrayList<Operation> getOperations() { return operations; }

    private void setBalance(double balance){ this.balance = balance; }

    public void setOverdraft(boolean overdraft){
        this.overdraft = overdraft;
    }

    public void registerOperation(Operation operation){
        this.operations.add(operation);
        setBalance(this.getBalance() + (operation.getAmount() - operation.getTax()));
    }

    public void addDependent(Client client){
        this.dependents.add(client);
    }

    public void removeDependent(Client client){
        this.dependents.remove(client);
    }

}
