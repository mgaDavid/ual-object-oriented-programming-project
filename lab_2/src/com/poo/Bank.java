package com.poo;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


public class Bank {
    private ArrayList<Client> clients = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private String choice;
    private int accountsCounter;

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

    private void returnMenu(){
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

    private void clientRecord(){
        IdDocument document = askDocument();

        if (clientExist(document)){
            System.out.println("Cliente já cadastrado na nossa base de dados.");
            returnMenu();
        } else {
            System.out.println("Introduza o nome do cliente");
            String name = scan.nextLine();

            Date birthday = valDate();

            System.out.println("Introduza a morada");
            String address = scan.nextLine();

            System.out.println("Introduza o email");
            String email = scan.nextLine();

            PhoneContact contact = valContact();
            clients.add(new Client(name, document, birthday, address, email, contact));
        }
    }

    private IdDocument askDocument(){
        try{
            System.out.println("Introduza o tipo do documento (passaporte ou BI/CC):");
            String documentType = scan.nextLine().strip().toUpperCase();
            System.out.println("Introduza o número do documento:");
            String document_number = scan.nextLine();

            ArrayList<String> validDocumentTypes = new ArrayList<>();

            validDocumentTypes.add("PASSAPORTE");
            validDocumentTypes.add("BI/CC");
            validDocumentTypes.add("CC");
            validDocumentTypes.add("BI");

            if (!validDocumentTypes.contains(documentType)){
                throw new Exception();
            }

            return new IdDocument(document_number, documentType);
        } catch (Exception e){
            System.out.println("O tipo de documento não é válido, tente novamente.");
            return askDocument();
        }
    }

    private boolean clientExist(IdDocument document) {
        for (Client thisClient : clients) {
            IdDocument this_client_id = thisClient.getIdNumber();
            if (this_client_id.getNumber() == document.getNumber() && this_client_id.getType() == document.getType()) {
                return true;
            }
        }

        return false;
    }

    private Date valDate(){
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

    private PhoneContact valContact(){
        System.out.println("Introduza o numero de telefone");
        String phoneString = scan.nextLine();
        try{
            int phoneNumber = Integer.parseInt(phoneString);
            System.out.println("Introduza o tipo contacto");
            String contactType = scan.nextLine();
            return new PhoneContact(phoneNumber, contactType);
        }catch (Exception e){
            System.out.println("O numero está incorreto, por favor apenas numeros");
            return valContact();
        }
    }

    private void changeRecord(){
        System.out.println("Introduza o numero de identificação do cliente");
        String document_number = scan.nextLine();

        for (Client thisClient : clients) {
            IdDocument this_client_id = thisClient.getIdNumber();
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
                        PhoneContact novoContacto = new PhoneContact(novoNumTelefone, novoTipoContacto);
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

    private void accountRecord(){
        IdDocument document = askDocument();

        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado, é necessário primeiro criar o cliente.");
        } else {
            Client client = getBankClient(document);
            System.out.println("Qual o valor do depósito inicial?");
            double initialDeposit = valAmount(scan.nextLine());

            System.out.println("Deseja associar um cliente dependente na conta? (S/N)");
            ArrayList<Client> otherClients = new ArrayList();
            boolean overdraft = false;

            accountsCounter += 1;
            Account newAccount = new Account(accountsCounter, client, otherClients, initialDeposit, overdraft);
            client.addAccount(newAccount);
        }

        returnMenu();
    }

    private double valAmount(String amount) {
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

    private void registerNewOperation(){
        IdDocument document = askDocument();

        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado.");
        } else {
            Client client = getBankClient(document);

            System.out.println("Insira o número da conta:");
            int accountId = valInt(scan.nextLine());

            if (!client.isMyAccount(accountId)){
                System.out.println("Não foi encontrada conta com esse identificador em suas contas.");
            } else {
                Account account = client.getAccount(accountId);
                System.out.println("Qual é o tipo de operação a ser realizada?");
                //Precisamos continuar daqui!!
            }

        }
        returnMenu();
    }

    private Client getBankClient(IdDocument document){
        Client thisClient = clients.get(0);
        for (Client client: clients){
            IdDocument documentToCompare = client.getIdNumber();
            if (documentToCompare.getNumber() == document.getNumber() && documentToCompare.getType() == document.getType()){
                thisClient = client;
                break;
            }
        }
        return thisClient;
    }

    private int valInt(String integer){
        try{
            return Integer.parseInt(integer);
        } catch (Exception e) {
            System.out.println("O número inserido não é um número inteiro, tente novamente:");
            return valInt(scan.nextLine());
        }
    }

}
