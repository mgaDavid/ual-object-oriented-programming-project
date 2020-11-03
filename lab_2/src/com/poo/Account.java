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

    public Object getOtherClients(){
        return otherClients;
    }

    public Object getBalance(){
        return balance;
    }

    public Object getOverdraft(){
        return overdraft;
    }

    public ArrayList<Operation> getOps() { return accountOps; }

    public void setMainClient(Client mainClient){
        this.mainClient = mainClient;
    }

    public void setOtherClients(ArrayList<Client> otherClients){
        this.otherClients = otherClients;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setOverdraft(boolean overdraft){
        this.overdraft = overdraft;
    }

    public boolean belongToClient(Client client){
        if (client.getIdNumber().getNumber() == mainClient.getIdNumber().getNumber()){
            return true;
        }

        if (otherClients.size() > 0){
            for (Client dependent: otherClients){
                if (dependent.getIdNumber().getNumber() == client.getIdNumber().getNumber()){
                    return true;
                }
            }
        }
        return false;
    }

}
