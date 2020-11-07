package com.poo;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.lang.System;

public class Bank {
    private ArrayList<Client> clients = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private int accountsCounter;
    private double tax = 0.42;

    public void menu() {
        System.out.println("\nDigite:");
        System.out.println("RC - para registo de cliente");
        System.out.println("AC - para alteração de dados de cliente");
        System.out.println("NC - para registo de conta");
        System.out.println("EC - para editar e consultar dados de conta");
        System.out.println("CD - para consultar dados de um cliente");
        System.out.println("S  - Sair");

        switch (getTreatedInput()) {
            case "RC" -> clientRecord();
            case "AC" -> changeClientRecord();
            case "NC" -> accountRecord();
            case "EC" -> manageAccount();
            case "CD" -> consultClient();
            case "S" -> {
                System.out.println("Encerrando..");
                System.exit(0);
            }
            default -> System.out.println("Opção inválida, tente novamente.");
        }

        menu();
    }

    private void consultClient() {
        IdDocument document = askDocument();

        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado na nossa base de dados.");
        } else {
            Client client = getBankClient(document);
            System.out.println("Nome: " + client.getName());
            System.out.println("Morada: " + client.getAddress());
            System.out.println("Tipo de contato: " + client.getContact().getType());
            System.out.println("Número do contato: " + client.getContact().getNumber());
            System.out.println("E-mail: " + client.getEmail());
            System.out.println("Data de nascimento: " + client.getBirthday());
        }
    }

    private void clientRecord(){
        IdDocument document = askDocument();

        if (clientExist(document)){
            System.out.println("Cliente já cadastrado na nossa base de dados.");
        } else {
            System.out.println("Introduza o nome do cliente");
            String name = getTreatedInput();

            Date birthday = validateDate();

            System.out.println("Introduza a morada");
            String address = getTreatedInput();

            System.out.println("Introduza o email");
            String email = getTreatedInput();

            PhoneContact contact = validateContact();
            clients.add(new Client(name, document, birthday, address, email, contact));
            System.out.println("Cliente registado com sucesso!");
        }
    }

    private IdDocument askDocument(){
        System.out.println("Introduza o tipo do documento (PASSAPORTE ou BI/CC):");
        String documentType = getTreatedInput();

        if (!Arrays.asList("PASSAPORTE", "BI/CC", "BI", "CC").contains(documentType)){
            System.out.println("O tipo de documento não é válido, tente novamente.");
            return askDocument();
        } else if (Arrays.asList("BI", "CC").contains(documentType)){
            documentType = "BI/CC";
        }

        System.out.println("Introduza o número do documento:");
        return new IdDocument(getTreatedInput(), documentType);
    }

    private boolean clientExist(IdDocument document) {
        for (Client thisClient : clients) {
            if (thisClient.equals(document)) {
                return true;
            }
        }

        return false;
    }

    private Date validateDate(){
        System.out.println("Introduza a data de nascimento - yyyy/mm/dd");
        String birthday = getTreatedInput();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try{
            return format.parse(birthday);
        } catch (ParseException e){
            System.out.println("O formato de data inserido está incorreto, por favor tente novamente");
            return validateDate();
        }
    }

    private PhoneContact validateContact(){
        System.out.println("Introduza o numero de telefone");
        String phoneString = getTreatedInput();
        try{
            int phoneNumber = Integer.parseInt(phoneString);
            System.out.println("Introduza o tipo contacto");
            String contactType = getTreatedInput();
            return new PhoneContact(phoneNumber, contactType);
        }catch (Exception e){
            System.out.println("O numero está incorreto, por favor introduza apenas números");
            return validateContact();
        }
    }

    private void editClient(Client client){
        System.out.println("\nDigite");
        System.out.println("1 - Para editar o nome do cliente");
        System.out.println("2 - Para editar a morada");
        System.out.println("3 - Para editar o email");
        System.out.println("4 - Para editar o contacto");
        System.out.println("5 - Para retornar ao menu anterior");

        String choice = getTreatedInput();

        switch (choice) {
            case "1" -> {
                System.out.println("Introduza o novo nome");
                client.setName(getTreatedInput());
            }
            case "2" -> {
                System.out.println("Introduza a nova morada");
                client.setAddress(getTreatedInput());
            }
            case "3" -> {
                System.out.println("Introduza o novo email");
                client.setEmail(getTreatedInput());
            }
            case "4" -> client.setContact(validateContact());
            case "5" -> {}
            default -> {
                System.out.println("Opção inválida, tente novamente.");
            }
        }

        if (!choice.equals("5")) editClient(client);

    }

    private void changeClientRecord(){
        IdDocument document = askDocument();

        if (clientExist(document)){
            editClient(getBankClient(document));
        } else {
            System.out.println("Cliente não existe na base de dados.");
        }
    }

    private void accountRecord(){
        IdDocument document = askDocument();

        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado, é necessário primeiro criar o cliente.");
        } else {
            Client client = getBankClient(document);
            System.out.println("Qual o valor do depósito inicial?");
            double initialDeposit = validateAmount(getTreatedInput());

            boolean overdraft = askOverdraft();

            System.out.println("Caso queira inserir dependentes à conta, deverá fazê-lo através do menu de edição de contas.");

            accountsCounter += 1;
            Account newAccount = new Account(accountsCounter, client, initialDeposit, overdraft);
            client.addAccount(newAccount);
            System.out.println("Conta número " + accountsCounter + " criada com sucesso!");
        }

    }

    private double validateAmount(String amount) {
        try {
            double deposit = Double.parseDouble(amount);
            boolean fail = (BigDecimal.valueOf(deposit).scale() > 4);

            if (fail){
                throw new Exception();
            }

            return deposit;
        } catch (Exception e){
            System.out.println("Valor inválido, insira outro valor (o valor inserido deve conter até 4 casas decimais):");
            return validateAmount(getTreatedInput());
        }
    }

    private void newOperation(Account account){
        String type = askOperationType();

        System.out.println("Qual o valor da operação?");
        double value = validateAmount(getTreatedInput());

        Operation operation = new Operation(type, value, this.tax);
        validateOperation(operation, account);
    }

    private Client getBankClient(IdDocument document){
        Client thisClient = clients.get(0);
        for (Client client: clients){
            if (client.equals(document)){
                thisClient = client;
                break;
            }
        }
        return thisClient;
    }

    private int validateInt(String integer){
        try{
            return Integer.parseInt(integer);
        } catch (Exception e) {
            System.out.println("O número inserido não é um número inteiro, tente novamente:");
            return validateInt(getTreatedInput());
        }
    }

    private String askOperationType(){
        try{
            System.out.println("Qual é o tipo de operação a ser realizada? (DÉBITO ou CRÉDITO)");
            String operationType = getTreatedInput();

            ArrayList<String> validOperationTypes = new ArrayList<>(Arrays.asList("DÉBITO", "CRÉDITO"));

            if (!validOperationTypes.contains(operationType)){
                throw new Exception();
            }

            return operationType;
        } catch (Exception e){
            System.out.println("O tipo de operação não é válido, tente novamente.");
            return askOperationType();
        }

    }

    private void validateOperation(Operation operation, Account account){
        if (account.getOverdraft() || operation.getType().equals("CRÉDITO") || account.getBalance() >= -operation.getAmount()){
            account.registerOperation(operation);
            System.out.println("Operação efetuada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    private void setNewDependents(Account account){
        IdDocument document = askDocument();
        if (!clientExist(document)) {
            System.out.println("Cliente não cadastrado, é necessário primeiro criar o cliente.");
        } else {
            Client dependent = getBankClient(document);

            System.out.println("Deseja excluir ou adicionar o dependente? (E/A)");

            switch (getTreatedInput()) {
                case "E" -> {
                    if (!dependent.isMyAccount(account.getNumber())) {
                        System.out.println("O cliente inserido não é um dependente dessa conta.");
                    } else {
                        if (account.getClient().equals(document)){
                            System.out.println("Você não pode excluir o titular da conta.");
                        } else {
                            account.removeDependent(dependent);
                            System.out.println("Cliente removido dos dependentes com sucesso!");
                        }
                    }
                }
                case "A" -> {
                    if (!dependent.isMyAccount(account.getNumber())) {
                        account.addDependent(dependent);
                        System.out.println("Cliente adicionado às dependentes com sucesso!");
                    } else {
                        System.out.println("O cliente inserido já é um dependente/titular dessa conta.");
                    }
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private boolean askOverdraft(){
        System.out.println("Deseja habilitar a conta à operações à descoberto? (S/N)");

        switch (getTreatedInput()){
            case "S" -> {
                return true;
            }
            case "N" -> {
                return false;
            }
            default -> {
                System.out.println("Resposta inválida, tente novamente.");
                return askOverdraft();
            }
        }
    }

    private String getTreatedInput() {
        return scan.nextLine().strip().toUpperCase();
    }

    public void manageAccount(){
        IdDocument document = askDocument();
        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado, é necessário primeiro criar o cliente.");
        } else {
            Client client = getBankClient(document);

            System.out.println("Insira o número da conta:");
            int accountId = validateInt(getTreatedInput());

            if (!client.isMyAccount(accountId)){
                System.out.println("Não foi encontrada conta com esse identificador nas contas deste cliente.");
            } else {
                Account account = client.getAccount(accountId);
                accountMenu(account);
            }
        }
    }

    public void accountMenu(Account account){
        System.out.println("\nDigite:");
        System.out.println("SC - Para consulta de saldo");
        System.out.println("M  - Para adicionar um movimento");
        System.out.println("LM - Para listar todos os movimentos");
        System.out.println("E  - Para editar os dados da conta");
        System.out.println("P  - Para retornar ao menu principal");

        String choice = getTreatedInput();

        switch (choice) {
            case "SC" -> System.out.println("O saldo da conta é: " + doubleToString(account.getBalance()));
            case "M" -> newOperation(account);
            case "LM" -> listOperations(account);
            case "E" -> editAccount(account);
            case "P" -> {}
            default -> System.out.println("Opção inválida, tente novamente.");
        }

        if (!choice.equals("P")) accountMenu(account);
        //accountMenu(account, choice);
        //if (!oldChoice.equals("P")){

    }

    private void listOperations(Account account) {
        for (Operation operation: account.getOperations()){
            System.out.println(operation);
        }
    }

    private void editAccount(Account account){
        System.out.println("\nDigite:");
        System.out.println("O - Para modificar o overdraft");
        System.out.println("D - Para editar os dependentes da conta");
        System.out.println("V - Para retornar ao menu da conta");

        String choice = getTreatedInput();

        switch (choice) {
            case "O" -> account.setOverdraft(askOverdraft());
            case "D" -> setNewDependents(account);
            case "V" -> {}
            default -> System.out.println("Opção inválida, tente novamente.");
        }

        if (!choice.equals("V")) editAccount(account);
    }

    public String doubleToString(double amount){
        return new DecimalFormat("0.0000").format(amount);
    }

}
