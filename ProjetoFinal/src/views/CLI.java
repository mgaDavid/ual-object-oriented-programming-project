package views;


import models.*;

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
        while (true) {
            try {
                switch (this.input.get(0)) {
                    case "RF" -> RF(this.input);
                    case "RC" -> RC(this.input);
                    case "RI" -> RI(this.input);
                    case "RL" -> RL(this.input);
                    case "RD" -> RD(this.input);
                    case "RE" -> RE(this.input);
                    case "CC" -> CC(this.input);
                    case "CI" -> CI(this.input);
                    case "CE" -> CE(this.input);
                    case "CF" -> CF(this.input);
                    case "G" -> G(this.input);
                    case "L" -> L(this.input);
                    case "" -> System.exit(0);
                    default -> throw new InvalidInstructionException();
                }
            } catch (InvalidInstructionException e) {
                System.out.println(e.getMessage());
            }
            this.input = split(scan.nextLine(), " ");
        }
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

    private LocalClass getLocal(int id) throws LocalNotFoundException {
        for (LocalClass local : this.getLocals()) {
            if (local.getId() == id) {
                return local;
            }
        }
        throw new LocalNotFoundException();
    }

    private EmployeeClass getEmployee(int id) throws EmployeeNotFoundException {
        for (EmployeeClass employee : this.getEmployees()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    private ClientClass getClient(int id) throws ClientNotFoundException {
        for (ClientClass client : this.getClients()) {
            if (client.getId() == id) {
                return client;
            }
        }
        throw new ClientNotFoundException();
    }

    private void RF(ArrayList<String> input) throws InvalidInstructionException {
        if (input.size() < 4) {
            throw new InvalidInstructionException();
        }

        try {
            String category = input.get(1);
            String permission = input.get(2);
            String name = String.join(" ", input.subList(3, input.size()));

            EmployeeClass newEmployee = new EmployeeClass(name, category, permission, this.getEmployees());
            this.employees.add(newEmployee);

            System.out.printf("Funcionário registado com o identificador %d.%n", newEmployee.getId());

        } catch (NonexistentCategoryException | ExistingEmployeeException | NonexistentPermissionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void RC(ArrayList<String> input) throws InvalidInstructionException {
        if (input.size() < 3) {
            throw new InvalidInstructionException();
        }

        try {
            // Validating employee
            int employeeId = Integer.parseInt(input.get(1));
            String name = String.join(" ", input.subList(2, input.size()));

            EmployeeClass employee = this.getEmployee(employeeId);

            // Validating if the employee is a manager
            if (!employee.getCategory().equals("Gestor")) {
                throw new NotAManagerException();
            }

            // Validating client
            ClientClass newClient = new ClientClass(name, employee, this.getClients());
            this.clients.add(newClient);

            System.out.printf("Cliente registado com o identificador %d.%n", newClient.getId());

        } catch (EmployeeNotFoundException | NotAManagerException | ExistingClientException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidInstructionException();
        }
    }

    private void RI(ArrayList<String> input) throws InvalidInstructionException {
        if (input.size() != 3) {
            throw new InvalidInstructionException();
        }

        try {
            int clientId = Integer.parseInt(input.get(1));
            //String newItemName = input.get(2);
            String newItemName = String.join(" ", input.subList(2, input.size()));
            ArrayList<String> permissions = split(scan.nextLine(), ",");

            ClientClass client = this.getClient(clientId);

            if (permissions.size() == 1 && permissions.get(0).equals("")) {
                permissions.set(0, "N");
            }

            client.addItem(newItemName, permissions);

            ItemClass newItem = client.getItems().get(client.getItems().size() - 1);
            System.out.printf("Item registado para o cliente %d com o identificador %d.%n", client.getId(),
                    newItem.getId());
        } catch (NumberFormatException e) {
            throw new InvalidInstructionException();
        } catch (ClientNotFoundException | NonexistentPermissionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void RL(ArrayList<String> input) throws InvalidInstructionException {
        if (input.size() < 2) {
            throw new InvalidInstructionException();
        }

        String newLocalName = String.join(" ", input.subList(1, input.size()));

        try {
            for (LocalClass local : this.getLocals()) {
                if (local.getName().equals(newLocalName)) {
                    throw new ExistingLocalException();
                }
            }

            LocalClass newLocal = new LocalClass(newLocalName);
            this.locals.add(newLocal);

            System.out.printf("Local registado com o identificador %d.%n", newLocal.getId());
        } catch (ExistingLocalException e) {
            System.out.println(e.getMessage());
        }

    }

    private void RD(ArrayList<String> input) throws InvalidInstructionException {
        if (input.size() != 3) {
            throw new InvalidInstructionException();
        }

        try {
            int clientId = Integer.parseInt(input.get(1));
            int localId = Integer.parseInt(input.get(2));

            ArrayList<String> employeesIds = split(scan.nextLine(), " ");
            ArrayList<String> itemQuantityPairsStrings = new ArrayList<>();

            while (true) {
                String itemQuantityPairString = scan.nextLine();
                if (itemQuantityPairString.equals("")) {
                    break;
                }

                if (split(itemQuantityPairString, " ").size() != 2) {
                    throw new InvalidInstructionException();
                }
                itemQuantityPairsStrings.add(itemQuantityPairString);
            }


            ClientClass client = this.getClient(clientId);
            LocalClass local = this.getLocal(localId);

            //ArrayList<DepositingItemClass> depositingItems = new ArrayList<>();
            final var depositingItems = new ArrayList<DepositingItemClass>();

            for (final var itemQuantityPairString : itemQuantityPairsStrings) {
                ArrayList<String> itemQuantityPair = split(itemQuantityPairString, " ");
                int itemId = Integer.parseInt(itemQuantityPair.get(0));
                int movingQuantity = Integer.parseInt(itemQuantityPair.get(1));
                depositingItems.add(new DepositingItemClass(client.getItem(itemId), movingQuantity));
            }

            EmployeeClass driver;
            int drivers = 0;
            ArrayList<EmployeeClass> employees = new ArrayList<>();

            for (String employeeIdAsString : employeesIds) {
                int employeeId = Integer.parseInt(employeeIdAsString);
                EmployeeClass employee = this.getEmployee(employeeId);
                if (employee.getCategory().equals("Condutor")) {
                    ++drivers;
                    if (drivers > 1) { throw new InvalidInstructionException();}
                    driver = employee;
                } else if (employee.getCategory().equals("Gestor")) {
                    throw new InvalidInstructionException();
                } else {
                    employees.add(employee);
                }
            }

            if (employees.size() == 0 || drivers == 0) {
                throw new InvalidInstructionException();
            }

            for (DepositingItemClass depositingItem : depositingItems) {
                ArrayList<String> itemPermissions = depositingItem.getItem().getPermissions();
                if (itemPermissions.contains("P")) {
                    if (!driver.getCategory().equals("P")) {
                        throw new DriverWithoutPermissionException();
                    }
                }

                if (itemPermissions.contains("S")) {
                    for (EmployeeClass employee : employees) {
                        if (!employee.getCategory().equals("S")) {
                            throw new LoaderWithoutPermissionException();
                        }
                    }
                }
            }
            employees.add(driver);
            client.addDeposit(local, employees, depositingItems);
            System.out.printf("Depósito registado com o identificador %d.%n", client.getLastDeposit().getId());

        } catch (NumberFormatException e) {
            throw new InvalidInstructionException();
        } catch (ClientNotFoundException | LocalNotFoundException | EmployeeNotFoundException | ItemNotFoundException |
                DriverWithoutPermissionException | LoaderWithoutPermissionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void RE(ArrayList<String> input) {
        int id = 1;
        System.out.printf("Entrega registada com o identificador %d.%n", id);
    }

    private void CC(ArrayList<String> input) throws InvalidInstructionException {
        if (input.size() < 2) {
            throw new InvalidInstructionException();
        }

        try {
            int clientId = Integer.parseInt(input.get(1));
            ClientClass client = this.getClient(clientId);
            EmployeeClass employee = client.getManager();


            System.out.println(client.getName());
            System.out.println(employee.getName());


        }catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void CI(ArrayList<String> input) {
        ;
    }

    private void CE(ArrayList<String> input) {
        ;
    }

    private void CF(ArrayList<String> input)  throws InvalidInstructionException {
        if (input.size() < 2) {
            throw new InvalidInstructionException();
        }
        try {
            int employeeId = Integer.parseInt(input.get(1));
            EmployeeClass employee = this.getEmployee(employeeId);

            System.out.println(employee.getName());
            System.out.println(employee.getPermission());


        }catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void G(ArrayList<String> input) {
        ;
    }

    private void L(ArrayList<String> input) {
        ;
    }

    private ArrayList<String> split(String string, String regex) {
        return new ArrayList<String>(Arrays.asList(string.split(String.format("%s", regex))));
    }
}
