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
            case "1":
                clientRecord();
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
            case "4":
                System.out.println("4");
                break;
            case "5":
                System.out.println("5");
                break;
            default:
                System.out.println("opção inválida, tente novamente.");
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
        System.out.println("Introdua o numero de telefone");
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
}
