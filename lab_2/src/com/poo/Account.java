package com.poo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.math.RoundingMode;


/**
 * In this class we have the Account constructor of our program
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 08/11/2020
 */

public class Account {

    private final int number;
    private Client client;
    private ArrayList<Client> dependents;
    private ArrayList<Operation> operations;
    private BigDecimal balance;
    private boolean overdraft;

    /**
     * This is the constructor for the Account class
     * We have the following parameters in our constructor
     *
     * @param number references the client number
     * @param client references the constructor client
     * @param initialDeposit references the initial deposit of the client
     * @param overdraft references if the client is allowed to perform overdraft on the balance
     */
    public Account(int number, Client client, double initialDeposit, boolean overdraft) {
        this.number = number;
        this.client = client;
        this.dependents = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.balance = new BigDecimal(0).setScale(4, RoundingMode.HALF_UP);
        this.overdraft = overdraft;
        if (initialDeposit > 0){
            this.registerOperation(new Operation("CRÉDITO", initialDeposit, new BigDecimal(0).setScale(2, RoundingMode.HALF_UP)));
        }
    }

    /**
     * method to return Account number
     * @return number (Account number)
     */
    public int getNumber(){ return this.number; }

    /**
     * method to return the list of the dependents connected to the Account
     * @return dependents (Array list of dependents)
     */
    public ArrayList<Client> getDependents(){
        return dependents;
    }

    /**
     * method to return the Balance of the account
     * @return balance (Current Balance)
     */
    public BigDecimal getBalance(){
        return this.balance;
    }

    /**
     * Boolean method to return if the client is allowed to perform Overdraft on the account
     * @return overdraft (True or False)
     */
    public boolean getOverdraft(){
        return overdraft;
    }

    /**
     * method to return the operations on the account
     * @return operations (All operations done in the Account)
     */
    public ArrayList<Operation> getOperations() { return operations; }

    /**
     * method to set the balance on the account
     * @param balance set current balance
     */
    private void setBalance(BigDecimal balance) { this.balance = balance.setScale(4, RoundingMode.HALF_UP); }

    /**
     * Boolean method to set if the customer is allowed to perform Overdraft in the account
     * @param overdraft (True or False)
     */
    public void setOverdraft(boolean overdraft){
        this.overdraft = overdraft;
    }

    /**
     * Method to register credit or debit operations in the account and set the balance
     * @param operation (current balance + operation - tax)
     */
    public void registerOperation(Operation operation) {
        this.operations.add(operation);
        setBalance(this.getBalance().add(operation.getAmount()).subtract(operation.getTax()));
    }

    /**
     * Method to add new dependent client to the account
     * @param client (new dependent client)
     */
    public void addDependent(Client client){
        this.dependents.add(client);
    }

    /**
     * Method to remove dependent from the Account
     * @param client (remove dependent client)
     */
    public void removeDependent(Client client){
        this.dependents.remove(client);
    }

    /**
     * Method to return Client object
     * @return client Object
     */
    public Client getClient() {
        return client;
    }
}
