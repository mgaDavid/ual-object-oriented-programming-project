package com.poo;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


public class menuPrincipal {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Account> accounts = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    String choice;

    public void menu() {
        System.out.println("RC - Registo de cliente");
        System.out.println("AC - Alteração de dados de cliente");
        System.out.println("NC - Registo de conta");
        System.out.println("M - Registo de movimento");
        System.out.println("SC - Consulta de saldo de conta");

        choice = scan.nextLine();

        switch (choice) {
            case "rc" -> clientRecord();
            case "ac" -> changeRecord();
            case "nc" -> accountRecord();
            case "m" -> registerNewOperation();
            case "sc" -> System.out.println("5");
            default -> System.out.println("opção inválida, tente novamente.");
        }

        returnMenu();
    }

    public void returnMenu(){
        System.out.println("Deseja retornar ao menu principal? (S/N)");
        choice = scan.nextLine();
        if (choice.equals("S")){
            menu();
        } else if (choice.equals("N")){
            System.out.println("Foi um prazer recebê-lo!");
        } else {
            System.out.println("Opção inválida, tente novamente.");
            returnMenu();
        }
    }

    public void clientRecord(){
        System.out.println("Introduza o tipo do documento:");
        String documentType = scan.nextLine();
        System.out.println("Introduza o número do documento:");
        String document_number = scan.nextLine();

        boolean goodToRecord = true;

        if (clients.size() > 0){
            for (Client thisClient : clients) {
                idDocument this_client_id = thisClient.getIdNumber();
                if (this_client_id.getNumber() == document_number && this_client_id.getType() == documentType) {
                    goodToRecord = false;
                    break;
                }
            }
        }

        if (!goodToRecord){
            System.out.println("Cliente já cadastrado na nossa base de dados.");
            returnMenu();
        } else {
            idDocument idNumber = new idDocument(document_number, documentType);

            System.out.println("Introduza o nome do cliente");
            String name = scan.nextLine();

            Date birthday = valDate();

            System.out.println("Introduza a morada");
            String address = scan.nextLine();

            System.out.println("Introduza o email");
            String email = scan.nextLine();

            phoneContact contact = valContact();
            clients.add(new Client(name, idNumber, birthday, address, email, contact));
        }
    }

    public Date valDate(){
        System.out.println("Introduza a data de nascimento - dd/mm/yyyy");
        String birthday = scan.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            return format.parse(birthday);
        } catch (ParseException e){
            System.out.println("O formato de data inserido está incorreto, por favor tente novamente");
            return valDate();
        }
    }

    public phoneContact valContact(){
        System.out.println("Introduza o numero de telefone");
        String phoneString = scan.nextLine();
        try{
            int phoneNumber = Integer.parseInt(phoneString);
            System.out.println("Introduza o tipo contacto");
            String contactType = scan.nextLine();
            return new phoneContact(phoneNumber, contactType);
        }catch (Exception e){
            System.out.println("O numero está incorreto, por favor apenas numeros");
            return valContact();
        }
    }

    public void changeRecord(){
        System.out.println("Introduza o numero de identificação do cliente");
        String document_number = scan.nextLine();

        for (Client thisClient : clients) {
            idDocument this_client_id = thisClient.getIdNumber();
            if (this_client_id.getNumber() == document_number) {
                System.out.println("1 - Nome do cliente");
                System.out.println("2 - Morada");
                System.out.println("3 - email");
                System.out.println("4 - Contacto");
                System.out.println("5 - Sair para menu principal");

                choice = scan.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("Introduza o novo nome");
                        String novoNome = scan.nextLine();
                        thisClient.setName(novoNome);
                    }
                    case "2" -> {
                        System.out.println("Introduza a nova morada");
                        String novaMorada = scan.nextLine();
                        thisClient.setAddress(novaMorada);
                    }
                    case "3" -> {
                        System.out.println("Introduza o novo email");
                        String novoEmail = scan.nextLine();
                        thisClient.setEmail(novoEmail);
                    }
                    case "4" -> {
                        System.out.println("Introduza o tipo de contacto");
                        String novoTipoContacto = scan.nextLine();
                        System.out.println("Introduza o numero de telefone");
                        String novoStrTelefone = scan.nextLine();
                        int novoNumTelefone = Integer.parseInt(novoStrTelefone);
                        phoneContact novoContacto = new phoneContact(novoNumTelefone, novoTipoContacto);
                        thisClient.setContact(novoContacto);
                    }
                    case "5" -> {
                    }
                }
                }else {
                System.out.println("Cliente não existe na base de dados.");
                returnMenu();
            }
        }
    }

    public void accountRecord(){
        System.out.println("Introduza numero de identificação do cliente");

        boolean goodToGo = false;
        for (Client thisClient: clients) {
            if (valClient(scan.nextLine())) {

                Account newAccount = new Account(accounts.size() + 1, thisClient);
                accounts.add(newAccount);

                System.out.println("Sua conta foi criada com sucesso. Deseja fazer um depósito inicial? (S/N)");
                String response = scan.nextLine();
                if (response.equals("S")){
                    System.out.println("Introduza o valor a ser depositado: (até 4 casas decimais)");
                    double amount = valAmount(scan.nextLine());
                    newAccount.setBalance(amount);
                }

                goodToGo = true;
                break;
            }
        }

        if (!goodToGo){
            System.out.println("Cliente não cadastrado, por favor cadastre o cliente antes de abrir a conta.");
        }
        returnMenu();
    }

    public double valAmount(String amount) {
        try {

            double deposit = Double.parseDouble(amount);
            boolean fail = (BigDecimal.valueOf(deposit).scale() > 4);

            if (fail){
                throw new Exception();
            }

            return deposit;
        } catch (Exception e){
            System.out.println("Valor inválido, insira outro valor (o valor inserido deve conter até 4 casas decimais):");
            return valAmount(scan.nextLine());
        }
    }

    public void registerNewOperation(){
        System.out.println("Introduza o número de identificação do cliente:");
        String clientId = scan.nextLine();
        if (!valClient(clientId)){
            System.out.println("Cliente não cadastrado.");
        } else {
            Client thisClient = getClientFromArray(clientId);

            System.out.println("Insira o número da conta:");
            int accountId = valInt(scan.nextLine());

            boolean accountExist = valAccount(accountId);
            if (!accountExist){
                System.out.println("A conta inserida não existe.");
            } else {
                Account thisAccount = getAccountFromArray(accountId);
                boolean belongToClient = thisAccount.belongToClient(thisClient);
                if (!belongToClient){
                    System.out.println("A conta introduzida não pertence a este cliente.");
                } else {
                    System.out.println("Qual é o tipo de operação a ser realizada?");
                    //Precisamos continuar daqui!!
                }
            }

        }
        returnMenu();
    }
    
    public boolean valClient(String id){
        for (Client thisClient: clients){
            if (thisClient.getIdNumber().getNumber() == id){
                return true;
            }
        }
        return false;
    }

    public Client getClientFromArray(String id){
        Client thisClient = clients.get(0);
        for (Client client: clients){
            if (client.getIdNumber().getNumber() == id){
                thisClient = client;
                break;
            }
        }
        return thisClient;
    }

    public boolean valAccount(int accountId){
        for (Account account: accounts){
            if (account.getAccountNumber() == accountId){
                return true;
            }
        }
        return false;
    }

    public Account getAccountFromArray(int accountId){
        Account thisAccount = accounts.get(0);
        for (Account account: accounts){
            if (account.getAccountNumber() == accountId){
                thisAccount = account;
                break;
            }
        }
        return thisAccount;
    }

    public int valInt(String integer){
        try{
            return Integer.parseInt(integer);
        } catch (Exception e) {
            System.out.println("O número inserido não é um número inteiro, tente novamente:");
            return valInt(scan.nextLine());
        }
    }
}
