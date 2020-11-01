package com.poo;

import java.net.ServerSocket;
import java.net.SocketOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class menuPrincipal {
    ArrayList<Client> clients = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    String choice;

    public void menu() {
        Boolean quit = false;

        System.out.println("1 - Registo de cliente");
        System.out.println("2 - Alteração de dados de cliente");
        System.out.println("3 - Registo de conta");
        System.out.println("4 - Registo de movimento");
        System.out.println("5 - Consulta de saldo de conta");

        choice = scan.nextLine();

        switch (choice) {
            case "1" -> clientRecord();
            case "2" -> changeRecord();
            case "3" -> System.out.println("3");
            case "4" -> System.out.println("4");
            case "5" -> System.out.println("5");
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
            for (Client this_client : clients) {
                idDocument this_client_id = this_client.getIdNumber();
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
        }
    }

    public Date valDate(){
        System.out.println("Introduza a data de nascimento - dd/mm/yyyy");
        String birthday = scan.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date date = format.parse(birthday);
            return date;
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
            phoneContact phone = new phoneContact(phoneNumber, contactType);
            return phone;
        }catch (Exception e){
            System.out.println("O numero está incorreto, por favor apenas numeros");
            return valContact();
        }
    }

    public void changeRecord(){
        System.out.println("Introduza o numero de identificação do cliente");
        String document_number = scan.nextLine();

        for (Client this_client : clients) {
            idDocument this_client_id = this_client.getIdNumber();
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
                        this_client.setName(novoNome);
                    }
                    case "2" -> {
                        System.out.println("Introduza a nova morada");
                        String novaMorada = scan.nextLine();
                        this_client.setAddress(novaMorada);
                    }
                    case "3" -> {
                        System.out.println("Introduza o novo email");
                        String novoEmail = scan.nextLine();
                        this_client.setEmail(novoEmail);
                    }
                    case "4" -> {
                        System.out.println("Introduza o tipo de contacto");
                        String novoTipoContacto = scan.nextLine();
                        System.out.println("Introduza o numero de telefone");
                        String novoStrTelefone = scan.nextLine();
                        int novoNumTelefone = Integer.parseInt(novoStrTelefone);
                        phoneContact novoContacto = new phoneContact(novoNumTelefone, novoTipoContacto);
                        this_client.setContact(novoContacto);
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
}
