package views;


import models.ClientClass;
import models.EmployeeClass;
import models.LocalClass;

import exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CLI {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<String> input = split(scan.nextLine(), " ");
    private String choice;
    private ArrayList<EmployeeClass> employees = new ArrayList<EmployeeClass>();
    private ArrayList<ClientClass> clients = new ArrayList<ClientClass>();
    private ArrayList<LocalClass> locals = new ArrayList<LocalClass>();

    public CLI() {
        while (!this.input.isEmpty()) {
            switch (this.input.get(0)) {
                case "RF" -> RF(this.input);
                case "RC" -> RC(this.input);
                case "RI" -> RI();
                case "RD" -> RD();
                case "RE" -> RE();
                case "CC" -> CC();
                case "CI" -> CI();
                case "CE" -> CE();
                case "CF" -> CF();
                case "G" -> G();
                case "L" -> L();
                default -> this.invalidInstruction();
            }
            this.input = split(scan.nextLine(), " ");
        }
        System.exit(0);
    }

    public ArrayList<String> getInput() {
        return input;
    }

    private void invalidInstruction() {
        System.out.println("Instrução inválida.");
    }

    public ArrayList<EmployeeClass> getEmployees() {
        return this.employees;
    }

    public ArrayList<ClientClass> getClients() {
        return clients;
    }

    public ArrayList<LocalClass> getLocals() {
        return locals;
    }

    private boolean existEmployee(String name) {
        for (EmployeeClass employee : this.getEmployees()) {
            if (employee.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private EmployeeClass getEmployee(int id) throws EmployeeNotFoundException {
        for (EmployeeClass employee : this.getEmployees()) {
            if (employee.getId() == id){
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Funcionário inexistente.");
    }

    private boolean existClient(String name) {
        for (ClientClass client : this.getClients()) {
            if (client.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean validateInputSize(ArrayList<String> input, int size) {
        if (input.size() < size) {
            invalidInstruction();
            return false;
        }
        return true;
    }

    private void RF(ArrayList<String> input) {
        System.out.println("entrei");
        //if (validateInputSize(input, 4)) {
        //    return;
        //}
        System.out.println("aqui");

        try {
            String category = input.get(1);
            System.out.println(category);
            String permission = input.get(2);
            System.out.println(permission);
            String name = String.join(" ", input.subList(3, input.size()));
            System.out.println(name);

            EmployeeClass newEmployee = new EmployeeClass(name, category, permission, this.getEmployees());
            this.employees.add(newEmployee);

            System.out.printf("Funcionário registado com o identificador %d.%n", newEmployee.getId());

        } catch (NonexistentCategoryException | ExistingEmployeeException | NonexistentPermissionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void RC(ArrayList<String> input) {
        if (validateInputSize(input, 3)) {
            return;
        }

        try {
            // Validating employee
            int employeeId = Integer.parseInt(input.get(1));
            EmployeeClass employee = this.getEmployee(employeeId);

            // Validating employee category
            if (!employee.getCategory().equals("Gestor")) {
                System.out.println("Funcionário incorreto.");
                return;
            }

            // Validating client
            String name = String.join(" ", input.subList(2, input.size()));
            if (existClient(name)) {
                System.out.println("Cliente existente.");
                return;
            }

            ClientClass newClient = new ClientClass(name);
            this.clients.add(newClient);

            System.out.printf("Cliente registado com o identificador %d.%n", newClient.getId());

        } catch (NumberFormatException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void RI() {
        int clienteId = 1;
        int itemId = 2;
        System.out.printf("Item registado para o cliente %d com o identificador %d.%n", clienteId, itemId);
    }

    private void RD() {
        int id = 1;
        System.out.printf("Depósito registado com o identificador %d.%n", id);
    }

    private void RE() {
        int id = 1;
        System.out.printf("Entrega registada com o identificador %d.%n", id);
    }

    private void CC() {
        ;
    }

    private void CI() {
        ;
    }

    private void CE() {
        ;
    }

    private void CF() {
        ;
    }

    private void G() {
        ;
    }

    private void L() {
        ;
    }

    private ArrayList<String> split(String string, String regex) {
        return new ArrayList<String>(Arrays.asList(string.split(String.format("%s", regex))));
    }
}

