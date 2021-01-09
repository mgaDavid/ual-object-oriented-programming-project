package com.poo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * In this class we have the Client constructor of our program
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 08/11/2020
 */

public class Client {

    private String name;
    private IdDocument document;
    private Date birthday;
    private String address;
    private String email;
    private PhoneContact contact;
    private ArrayList<Account> myAccounts;

    /**
     * This is the constructor for the client Object
     * We have the following parameters in our constructor
     * @param name (Name of the client)
     * @param document (Identification document of the client (BI/CC or Passport)
     * @param birthday (Birthday of the client)
     * @param address (Address of the client)
     * @param email (email of the client)
     * @param contact (Contact information of the client)
     */
    public Client(String name, IdDocument document, Date birthday, String address, String email, PhoneContact contact){
        this.name = name;
        this.document = document;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.myAccounts = new ArrayList<>();
    }

    /**
     * Method to get identification information of the client
     * References Class IdDocument
     * @return document (ID documentation of the client)
     */
    public IdDocument getDocument(){
        return this.document;
    }

    /**
     * Method to retrieve Array list of Client Accounts
     * @return myAccounts (list of Client Accounts)
     */
    public ArrayList<Account> getAccounts() { return this.myAccounts; }

    /**
     * Method to set name of the client
     * @param name (Name of Client)
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method to set the Address of Client
     * @param address (Address of Client)
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Method to set email of client
     * @param email (Email of Client)
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Method to set contact of client
     * It references Class PhoneContact
     *
     * @param contact (Set customer contact)
     */
    public void setContact(PhoneContact contact){
        this.contact = contact;
    }

    /**
     * Method to add Account to client's list of Accounts
     * @param account (Account object)
     */
    public void addAccount(Account account){ this.myAccounts.add(account); }

    /**
     * Method to retrieve Account
     * @param accountId (Account ID)
     * @return Account (Return Account searched)
     */
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

    /**
     * Boolean method to verify is the Account belongs to client
     *
     * @param accountId (Account ID)
     * @return True or False
     */
    public boolean isMyAccount(int accountId){
        for (Account account: this.getAccounts()){
            if (account.getNumber() == accountId){
                return true;
            }
        }
        return false;
    }

    /**
     * Boolean method to verify if the document information we are searching matches the documents in Database
     * @param document (document of client - BI/CC Passport and number)
     * @return True or False
     */
    public boolean equals(IdDocument document){ return document.equals(this.getDocument()); }

    /**
     * Method to get Birthday date from Client
     * @return birthday
     */
    public String getBirthday() {
        return new SimpleDateFormat("yyyy/MM/dd").format(this.birthday);
    }

    /**
     * Method to return address of Client
     * @return address of clint
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to return name of client
     * @return name of client
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return email of client
     * @return email of client
     */
    public String getEmail() {
        return email;
    }

    /**
     * method to return Client contact information
     * @return contact (BI/CC or Passport and number
     */
    public PhoneContact getContact() {
        return contact;
    }

    /**
     * Method to remove Account from Client list of Accounts
     * @param account (Account information)
     */
    public void removeAccount(Account account){
        this.myAccounts.remove(account);
    }
}
