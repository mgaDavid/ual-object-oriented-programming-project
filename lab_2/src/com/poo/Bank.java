package com.poo;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import static java.lang.Math.abs;


public class Bank {
    private ArrayList<Client> clients = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private String choice;
    private int accountsCounter;
    private double tax = 0.42;
    private Client thisClient;

    public void menu() {
        System.out.println("RC - Registo de cliente");
        System.out.println("AC - Alteração de dados de cliente");
        System.out.println("NC - Registo de conta");
        System.out.println("M - Registo de movimento");
        System.out.println("EC - Editar e consultar dados da conta:");
        System.out.println("ECC - Edita uma conta");

        choice = scan.nextLine().strip().toUpperCase();

        switch (choice) {
            case "RC" -> clientRecord();
            case "AC" -> changeRecord();
            case "NC" -> accountRecord();
            case "M" -> newOperation();
            case "EC" -> editAndConsultAccount();
            case "ECC" -> editAccount();
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
            System.out.println("Encerrando..");
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

            Date birthday = validateDate();

            System.out.println("Introduza a morada");
            String address = scan.nextLine();

            System.out.println("Introduza o email");
            String email = scan.nextLine();

            PhoneContact contact = validateContact();
            clients.add(new Client(name, document, birthday, address, email, contact));
            returnMenu();
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
            validDocumentTypes.add("BI");
            validDocumentTypes.add("CC");

            if (!validDocumentTypes.contains(documentType)){
                throw new Exception();
            } else if (documentType.equals("CC") || documentType.equals("BI")){
                documentType = "BI/CC";
            }

            return new IdDocument(document_number, documentType);
        } catch (Exception e){
            System.out.println("O tipo de documento não é válido, tente novamente.");
            return askDocument();
        }
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
        String birthday = scan.nextLine();
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
        String phoneString = scan.nextLine();
        try{
            int phoneNumber = Integer.parseInt(phoneString);
            System.out.println("Introduza o tipo contacto");
            String contactType = scan.nextLine();
            return new PhoneContact(phoneNumber, contactType);
        }catch (Exception e){
            System.out.println("O numero está incorreto, por favor apenas numeros");
            return validateContact();
        }
    }

    private void changeRecord(){
        System.out.println("Introduza o numero de identificação do cliente");
        String document_number = scan.nextLine();

        for (Client thisClient : clients) {
            IdDocument this_client_id = thisClient.getDocument();
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
            double initialDeposit = validateAmount(scan.nextLine());

            boolean overdraft = askOverdraft();

            System.out.println("Caso queria inserir dependentes à conta, deverá fazê-lo no menu principal.");
            ArrayList<Client> otherClients = new ArrayList<>();

            accountsCounter += 1;
            Account newAccount = new Account(accountsCounter, client, otherClients, initialDeposit, overdraft);
            client.addAccount(newAccount);
            for (Client client1: otherClients){
                client.addAccount(newAccount);
            }
        }

        returnMenu();
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
            return validateAmount(scan.nextLine());
        }
    }

    private void newOperation(){
        IdDocument document = askDocument();

        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado.");
        } else {
            Client client = getBankClient(document);

            System.out.println("Insira o número da conta:");
            int accountId = validateInt(scan.nextLine());

            if (!client.isMyAccount(accountId)){
                System.out.println("Não foi encontrada conta com esse identificador nas contas deste cliente.");
            } else {
                Account account = client.getAccount(accountId);

                String type = askOperationType();

                System.out.println("Qual o valor da operação?");
                double value = validateAmount(scan.nextLine());

                Operation operation = new Operation(type, value, tax);
                validadeOperation(operation, account);
            }

        }
        returnMenu();
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
            return validateInt(scan.nextLine());
        }
    }

    private String askOperationType(){
        try{
            System.out.println("Qual é o tipo de operação a ser realizada? (DÉBITO ou CRÉDITO)");
            String operationType = scan.nextLine().strip().toUpperCase();

            ArrayList<String> validOperationTypes = new ArrayList<>();

            validOperationTypes.add("DÉBITO");
            validOperationTypes.add("CRÉDITO");

            if (!validOperationTypes.contains(operationType)){
                throw new Exception();
            }

            return operationType;
        } catch (Exception e){
            System.out.println("O tipo de operação não é válido, tente novamente.");
            return askOperationType();
        }

    }

    private void validadeOperation(Operation operation, Account account){
        if (account.getOverdraft() || operation.getType().equals("CRÉDITO") || account.getBalance() >= -operation.getAmount()){
            account.registerOperation(operation, this.tax);
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
            choice = scan.nextLine().strip().toUpperCase();

            if (choice.equals("E")){
                if (!account.getOtherClients().contains(dependent)){
                    
                }

            } else if (choice.equals("A")){
                account.addDependent(dependent);
            } else {
                System.out.println("Opção inválida, tente novamente.");
                editAccount(account);
            }
        }
    }

    private boolean askOverdraft(){
        System.out.println("Deseja habilitar a conta à operações à descoberto? (S/N)");
        choice = scan.nextLine().strip().toUpperCase();

        if (choice.equals("S")){
            return true;
        } else if (!choice.equals("N")){
            System.out.println("Resposta inválida, tente novamente.");
            return askOverdraft();
        }
        return false;
    }

    private void editAccount(Account account){
        System.out.println("Deseja modificar o overdraft ou os dependentes da conta? (O/D)");
        choice = scan.nextLine().strip().toUpperCase();
        if (choice.equals("O")){
            account.setOverdraft(askOverdraft());
        } else if (choice.equals("D")){
            setNewDependents(account);
        } else {
            System.out.println("Opção inválida, tente novamente.");
            editAccount(account);
        }
    }

    public void editAndConsultAccount(){
        IdDocument document = askDocument();
        if (!clientExist(document)){
            System.out.println("Cliente não cadastrado, é necessário primeiro criar o cliente.");
        } else {
            Client client = getBankClient(document);

            System.out.println("Insira o número da conta:");
            int accountId = validateInt(scan.nextLine());

            if (!client.isMyAccount(accountId)){
                System.out.println("Não foi encontrada conta com esse identificador nas contas deste cliente.");
            } else {
                Account account = client.getAccount(accountId);
                System.out.println("Deseja consultar o saldo ou modificar os dados da conta? (S/M)");
                choice = scan.nextLine().strip().toUpperCase();
                if (choice.equals("M")){
                    editAccount(account);
                } else if (choice.equals("S")){
                    System.out.println("O saldo da conta é: " + account.getBalance());
                }
            }
        }
    }
}
