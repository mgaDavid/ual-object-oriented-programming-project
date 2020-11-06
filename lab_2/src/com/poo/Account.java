package com.poo;

import java.util.ArrayList;

public class Account {

    private int accountNumber;
    private Client mainClient;
    private ArrayList<Client> otherClients;
    private ArrayList<Operation> accountOps;
    private double balance;
    private boolean overdraft;

    public Account(
            int accountNumber,
            Client mainClient,
            ArrayList<Client> otherClients,
            double initialDeposit,
            boolean overdraft
    ) {

        this.accountNumber = accountNumber;
        this.mainClient = mainClient;
        this.otherClients = otherClients;
        this.balance = initialDeposit;
        this.overdraft = overdraft;
        this.accountOps = new ArrayList<>();
    }

    public int getAccountNumber(){ return this.accountNumber; }

    public Client getMainClient(){
        return mainClient;
    }

    public ArrayList<Client> getOtherClients(){
        return otherClients;
    }

    public double getBalance(){
        return balance;
    }

    public boolean getOverdraft(){
        return overdraft;
    }

    public ArrayList<Operation> getOps() { return accountOps; }

    public void setMainClient(Client mainClient){
        this.mainClient = mainClient;
    }

    public void setOtherClients(ArrayList<Client> otherClients){
        this.otherClients = otherClients;
    }

    public void setBalance(double balance){ this.balance += balance; }

    public void setOverdraft(boolean overdraft){
        this.overdraft = overdraft;
    }

    public void registerOperation(Operation operation, double tax){
        this.accountOps.add(operation);
        setBalance(operation.getAmount() - tax);
    }

    public void addDependent(Client client){
        this.otherClients.add(client);
    }

    public void removeDependent(Client client){
        this.otherClients.remove(client);
    }

}
