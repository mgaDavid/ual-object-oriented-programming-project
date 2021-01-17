package views;


import models.*;

import exceptions.*;

import java.io.*;
import java.util.*;

public class CLI {
    private final Scanner scan = new Scanner(System.in);
    private List<EmployeeClass> employees = new ArrayList<>();
    private List<ClientClass> clients = new ArrayList<>();
    private List<LocalClass> locals = new ArrayList<>();
    private static final PermissionClass NormalPermission =
            new PermissionClass(1, "N", "Normal", "Autorização padrão.");
    private static final PermissionClass SecurePermission =
            new PermissionClass(2, "S", "Seguro", "Autorizado a interagir com itens com nível elevado de segurança.");
    private static final PermissionClass DangerousPermission =
            new PermissionClass(3, "P", "Perigoso", "Autorizado a interagir com itens perigosos.");
    private static final CategoryClass driverCategory = new CategoryClass("Condutor", "Conduz veículos.");
    private static final CategoryClass loaderCategory =
            new CategoryClass("Carregador", " Efetua carga e descarga de veículos.");
    private static final CategoryClass managerCategory = new CategoryClass("Gestor", "Gere a relação com um cliente.");

    public CLI() {
        while (true) {
            try {
                var input = split(scan.nextLine(), " ");
                try {
                    switch (input.get(0)) {
                        case "RF" -> RF(input);
                        case "RC" -> RC(input);
                        case "RI" -> RI(input);
                        case "RL" -> RL(input);
                        case "RD" -> RD(input);
                        case "RE" -> RE(input);
                        case "CC" -> CC(input);
                        case "CI" -> CI(input);
                        case "CE" -> CE(input);
                        case "CF" -> CF(input);
                        case "G" -> G(input);
                        case "L" -> L(input);
                        case "" -> System.exit(0);
                        default -> throw new InvalidInstructionException();
                    }
                } catch (InvalidInstructionException | NonexistentCategoryException | NonexistentPermissionException |
                        ExistingEmployeeException | EmployeeNotFoundException | NotAManagerException |
                        ExistingClientException | ClientNotFoundException | InvalidPermissionException |
                        ExistingLocalException | QuantityInsufficientException | ItemNotFoundException |
                        DriverWithoutPermissionException | LocalNotFoundException | LoaderWithoutPermissionException|
                        DeliveryNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }
    }

    public List<EmployeeClass> getEmployees() {
        return this.employees;
    }

    public List<ClientClass> getClients() {
        return this.clients;
    }

    public List<LocalClass> getLocals() {
        return this.locals;
    }

    private LocalClass getLocal(int id) throws LocalNotFoundException {
        for (final var local : this.getLocals()) {
            if (local.getId() == id) {
                return local;
            }
        }
        throw new LocalNotFoundException();
    }

    private EmployeeClass getEmployee(int id) throws EmployeeNotFoundException {
        for (final var employee : this.getEmployees()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    private boolean ExistEmployeeInCategory(String name, CategoryClass category) {
        for (final var employee : this.getEmployees()) {
            if ((employee.getName().equals(name)) && (employee.getCategory().equals(category))){
                return true;
            }
        }
        return false;
    }

    private boolean ExistEmployee(String name) {
        for (final var employee : this.getEmployees()) {
            if (employee.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean ExistClient(String name) {
        for (final var client : this.getClients()) {
            if (client.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean ExistLocal(String name) {
        for (final var local : this.getLocals()) {
            if (local.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private int getLastEmployeeId() {
        return this.getEmployees().size();
    }

    private int getLastClientId() {
        return this.getClients().size();
    }

    private int getLastLocalId() {
        return this.getLocals().size();
    }

    private ClientClass getClient(int id) throws ClientNotFoundException {
        for (final var client : this.getClients()) {
            if (client.getId() == id) {
                return client;
            }
        }
        throw new ClientNotFoundException();
    }

    private void RF(List<String> input)
            throws InvalidInstructionException, NonexistentCategoryException, NonexistentPermissionException,
            ExistingEmployeeException {
        if (input.size() < 4) {
            throw new InvalidInstructionException();
        }

        final var category = input.get(1);
        final var permissionInitials = input.get(2);
        final var name = String.join(" ", input.subList(3, input.size()));

        CategoryClass categoryType;
        switch (category) {
            case "Condutor" -> categoryType = driverCategory;
            case "Gestor" -> categoryType = managerCategory;
            case "Carregador" -> categoryType = loaderCategory;
            default -> throw new NonexistentCategoryException();
        }

        var permission = NormalPermission;
        switch (permissionInitials) {
            case "N" -> {
            }
            case "S" -> {
                if (!category.equals("Carregador")) {
                    throw new NonexistentPermissionException();
                }
                permission = SecurePermission;

            }
            case "P" -> {
                if (!category.equals("Condutor")) {
                    throw new NonexistentPermissionException();
                }
                permission = DangerousPermission;
            }
            default -> throw new InvalidInstructionException();
        }

        final var id = this.getLastEmployeeId() + 1;

        // To avoid creating employees with repeated names
        if (ExistEmployeeInCategory(name, categoryType)) {
            throw new ExistingEmployeeException();
        }

        final var newEmployee = new EmployeeClass(id, name, permission, categoryType);
        this.employees.add(newEmployee);
        System.out.printf("Funcionário registado com o identificador %d.%n", newEmployee.getId());
    }

    private void RC(List<String> input)
            throws InvalidInstructionException, EmployeeNotFoundException, NotAManagerException,
            ExistingClientException {
        if (input.size() < 3) {
            throw new InvalidInstructionException();
        }

        int employeeId = Integer.parseInt(input.get(1));
        String name = String.join(" ", input.subList(2, input.size()));

        // Validating employee
        final var manager = this.getEmployee(employeeId);

        // Validating if the employee is a manager
        if (!(manager.getCategory().equals(managerCategory))) {
            throw new NotAManagerException();
        }

        // To avoid creating clients with repeated names
        if (ExistClient(name)) {
            throw new ExistingClientException();
        }

        final var id = this.getLastClientId() + 1;
        ClientClass newClient = new ClientClass(id, name, manager);
        this.clients.add(newClient);

        System.out.printf("Cliente registado com o identificador %d.%n", newClient.getId());
    }

    private void RI(List<String> input)
            throws InvalidInstructionException, ClientNotFoundException, InvalidPermissionException {
        if (input.size() != 3) {
            throw new InvalidInstructionException();
        }

        int clientId = Integer.parseInt(input.get(1));
        String name = input.get(2);
        final var permissionsInitials = split(scan.nextLine(), ",");

        for (final var permissionInitials : permissionsInitials) {
            switch (permissionInitials) {
                case "", "N", "S", "P" -> {
                }
                default -> throw new InvalidPermissionException();
            }
        }

        final var client = this.getClient(clientId);

        final var permissions = new ArrayList<PermissionClass>();
        for (final var permissionInitials : permissionsInitials) {
            var permission = NormalPermission;
            switch (permissionInitials) {
                case "", "N" -> {
                }
                case "S" -> permission = SecurePermission;
                case "P" -> permission = DangerousPermission;
            }
            if (!permissions.contains(permission)) {
                permissions.add(permission);
            }
        }

        final var id = client.getLastItemId() + 1;
        final var item = new ItemClass(id, name, client, permissions);
        item.sortPermissions();
        client.addItem(item);

        System.out.printf("Item registado para o cliente %d com o identificador %d.%n", client.getId(), item.getId());
    }

    private void RL(List<String> input) throws InvalidInstructionException, ExistingLocalException {
        if (input.size() < 2) {
            throw new InvalidInstructionException();
        }

        String name = String.join(" ", input.subList(1, input.size()));

        if (this.ExistLocal(name)) {
            throw new ExistingLocalException();
        }

        final var id = this.getLastLocalId() + 1;
        LocalClass local = new LocalClass(id, name);
        this.locals.add(local);

        System.out.printf("Local registado com o identificador %d.%n", local.getId());
    }

    private int RDOrE(List<String> input, String method)
            throws InvalidInstructionException, ClientNotFoundException, LocalNotFoundException,
            EmployeeNotFoundException, ItemNotFoundException, DriverWithoutPermissionException,
            LoaderWithoutPermissionException, QuantityInsufficientException {
        if (input.size() != 3) {
            throw new InvalidInstructionException();
        }

        int clientId = Integer.parseInt(input.get(1));
        int localId = Integer.parseInt(input.get(2));

        final var employeesIds = split(scan.nextLine(), " ");
        final var itemQuantityPairsStrings = new ArrayList<String>();

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

        final var transactingItems = new ArrayList<TransactedItemClass>();

        for (final var itemQuantityPairString : itemQuantityPairsStrings) {
            final var itemQuantityPair = split(itemQuantityPairString, " ");
            int itemId = Integer.parseInt(itemQuantityPair.get(0));
            int movingQuantity = Integer.parseInt(itemQuantityPair.get(1));
            transactingItems.add(new TransactedItemClass(client.getItem(itemId), movingQuantity));
        }

        if (method.equals("delivery")) {
            for (final var transactedItem : transactingItems) {
                if (transactedItem.getItem().getQuantity() < transactedItem.getTransactedQuantity()) {
                    throw new QuantityInsufficientException();
                }
            }
        }

        int drivers = 0;
        final var employees = new ArrayList<EmployeeClass>();

        for (String employeeIdAsString : employeesIds) {
            int employeeId = Integer.parseInt(employeeIdAsString);
            EmployeeClass employee = this.getEmployee(employeeId);
            if (employee.getCategory().equals(driverCategory)) {
                ++drivers;
                if (drivers > 1) {
                    throw new InvalidInstructionException();
                }
            } else if (employee.getCategory().equals(managerCategory)) {
                throw new InvalidInstructionException();
            }
            employees.add(employee);
        }

        if (employees.size() < 2 || drivers == 0) {
            throw new InvalidInstructionException();
        }

        final var itemsPermissions = new ArrayList<PermissionClass>();

        for (final var transactedItem : transactingItems) {
            for (final var permission : transactedItem.getItem().getPermissions()) {
                if (!itemsPermissions.contains(permission)) {
                    itemsPermissions.add(permission);
                }
            }
        }

        if (itemsPermissions.contains(DangerousPermission)) {
            for (final var employee : employees) {
                if ((employee.getCategory().equals(driverCategory)) &&
                        (!employee.getPermission().equals(DangerousPermission))) {
                    throw new DriverWithoutPermissionException();
                }
            }
        }

        var loaderHasPermission = false;

        if (!itemsPermissions.contains(SecurePermission)) {
            loaderHasPermission = true;
        } else {
            for (final var employee : employees) {
                if ((employee.getCategory().equals(loaderCategory)) &&
                        (employee.getPermission().equals(SecurePermission))) {
                    loaderHasPermission = true;
                    break;
                }
            }
        }

        if (!loaderHasPermission) {
            throw new LoaderWithoutPermissionException();
        }

        TransactionClass transaction;
        int id;
        if (method.equals("deposit")) {
            id = client.getLastDepositId() + 1;
            transaction = new TransactionClass(id, client, local, employees, transactingItems);
            client.addDeposit(transaction);
        } else {
            id = client.getLastDeliveryId() + 1;
            transaction = new TransactionClass(id, client, local, employees, transactingItems);
            client.addDelivery(transaction);
        }

        transaction.sortEmployees();
        transaction.sortItems();
        return transaction.getId();
    }

    private void RD(List<String> input)
            throws InvalidInstructionException, QuantityInsufficientException, ItemNotFoundException,
            DriverWithoutPermissionException, LocalNotFoundException, ClientNotFoundException,
            EmployeeNotFoundException, LoaderWithoutPermissionException {
        int id = RDOrE(input, "deposit");
        System.out.printf("Depósito registado com o identificador %d.%n", id);
    }

    private void RE(List<String> input)
            throws InvalidInstructionException, QuantityInsufficientException, ItemNotFoundException,
            DriverWithoutPermissionException, LocalNotFoundException, ClientNotFoundException,
            EmployeeNotFoundException, LoaderWithoutPermissionException {
        int id = RDOrE(input, "delivery");
        System.out.printf("Entrega registada com o identificador %d.%n", id);
    }

    private void CC(List<String> input) throws InvalidInstructionException, ClientNotFoundException {
        if (input.size() != 2) throw new InvalidInstructionException();

        int clientId = Integer.parseInt(input.get(1));
        final var client = this.getClient(clientId);
        final var manager = client.getManager();

        System.out.println(client.getName());
        System.out.println(manager.getName());

        client.sortItems();
        System.out.println("Items:");
        for (final var item : client.getItems()) {
            System.out.printf("  %d (%d) [%s] %s%n", item.getId(), item.getQuantity(), item.getPermissionsInitials(),
                    item.getName());
        }

        System.out.println("Depósitos:");
        for (final var deposit : client.getDeposits()) {
            System.out.printf("  %d (%s)%n", deposit.getId(), deposit.getLocal().getName());
        }

        System.out.println("Entregas:");
        for (final var delivery : client.getDeliveries()) {
            System.out.printf("  %d (%s)%n", delivery.getId(), delivery.getLocal().getName());
        }
    }

    private void CI(List<String> input)
            throws InvalidInstructionException, ClientNotFoundException, ItemNotFoundException {
        if (input.size() != 3) throw new InvalidInstructionException();

        int clientId = Integer.parseInt(input.get(1));
        final var itemId = Integer.parseInt(input.get(2));

        final var client = this.getClient(clientId);
        final var item = client.getItem(itemId);

        System.out.printf("%d [%s] %s%n", item.getQuantity(), item.getPermissionsNames(), item.getName());

        System.out.println("Depósitos:");
        for (final var deposit : client.getDeposits()) {
            for (final var transactedItem : deposit.getItems()) {
                if (transactedItem.getItem().equals(item)) {
                    System.out.printf("  %d %d%n", deposit.getId(), transactedItem.getTransactedQuantity());
                }
            }
        }

        System.out.println("Entregas:");
        for (final var delivery : client.getDeliveries()) {
            for (final var transactedItem : delivery.getItems()) {
                if (transactedItem.getItem().equals(item)) {
                    System.out.printf("  %d %d%n", delivery.getId(), transactedItem.getTransactedQuantity());
                }
            }
        }
    }

    private void CE(List<String> input)
            throws InvalidInstructionException, ClientNotFoundException, DeliveryNotFoundException {
        if (input.size() != 3) throw new InvalidInstructionException();

        final var clientId = Integer.parseInt(input.get(1));
        final var deliveryId = Integer.parseInt(input.get(2));

        final var client = this.getClient(clientId);
        final var delivery = client.getDelivery(deliveryId);

        System.out.println(delivery.getLocal().getName());

        final var driver = delivery.getDriver();
        System.out.printf("%s %s%n", driver.getPermission().getDescription(), driver.getName());

        for (final var loader : delivery.getLoaders()) {
            System.out.printf("%s %s%n", loader.getPermission().getDescription(), loader.getName());
        }

        for (final var transactedItem : delivery.getItems()) {
            final var item = transactedItem.getItem();
            System.out.printf("%d %d %s%n", item.getId(), transactedItem.getTransactedQuantity(), item.getName());
        }
    }

    private void CF(List<String> input) throws InvalidInstructionException, EmployeeNotFoundException {
        if (input.size() < 2) throw new InvalidInstructionException();

        final var employeeId = Integer.parseInt(input.get(1));
        final var employee = this.getEmployee(employeeId);

        System.out.println(employee.getName());
        System.out.println(employee.getCategory().getCategoryName());
        System.out.println(employee.getPermission().getName());

        final var deposits = new ArrayList<TransactionClass>();
        final var deliveries = new ArrayList<TransactionClass>();

        for (final var client : this.getClients()) {
            for (final var deposit : client.getDeposits()) {
                if (deposit.getEmployees().contains(employee)) {
                    deposits.add(deposit);
                }
            }

            for (final var delivery : client.getDeliveries()) {
                if (delivery.getEmployees().contains(employee)) {
                    deliveries.add(delivery);
                }
            }
        }

        sortTransactions(deposits);
        sortTransactions(deliveries);

        System.out.println("Depósitos:");
        for (final var deposit : deposits) {
            System.out.printf("  %d %d (%s) %s%n", deposit.getClient().getId(), deposit.getId(),
                    deposit.getLocal().getName(), deposit.getClient().getName());
        }

        System.out.println("Entregas:");
        for (final var delivery : deliveries) {
            System.out.printf("  %d %d (%s) %s%n", delivery.getClient().getId(), delivery.getId(),
                    delivery.getLocal().getName(), delivery.getClient().getName());
        }
    }

    private void G(List<String> input) throws InvalidInstructionException {
        if (input.size() != 2) throw new InvalidInstructionException();

        final var name = input.get(1);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(name);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new SaveClass(this.getEmployees(), this.getClients(), this.getLocals()));
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Ficheiro gravado com sucesso.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void L(List<String> input) throws InvalidInstructionException {
        if (input.size() != 2) throw new InvalidInstructionException();

        final var name = input.get(1);

        try {
            FileInputStream fileInputStream = new FileInputStream(name);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            final var saved = (SaveClass) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            setEmployees(saved.getEmployees());
            setClients(saved.getClients());
            setLocals(saved.getLocals());
            System.out.println("Ficheiro lido com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro inexistente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> split(String string, String regex) {
        return new ArrayList<>(Arrays.asList(string.split(String.format("%s", regex))));
    }

    public void sortTransactions(List<TransactionClass> transactions) {
        transactions.sort((transactedItem1, transactedItem2) -> {
            int clientCmp = transactedItem1.getClient().getName().compareTo(transactedItem2.getClient().getName());
            if (clientCmp != 0) {
                return clientCmp;
            }
            int localCmp = transactedItem1.getLocal().getName().compareTo(transactedItem2.getLocal().getName());
            if (localCmp != 0) {
                return localCmp;
            }
            return Integer.compare(transactedItem1.getId(), transactedItem2.getId());
        });
    }

    public void setEmployees(List<EmployeeClass> employees) {
        this.employees = employees;
    }

    public void setClients(List<ClientClass> clients) {
        this.clients = clients;
    }

    public void setLocals(List<LocalClass> locals) {
        this.locals = locals;
    }

    public static CategoryClass getCategory(String categoryName) {
        switch (categoryName) {
            case "Condutor" -> {
                return driverCategory;
            }
            case "Carregador" -> {
                return loaderCategory;
            }
            default -> {
                return managerCategory;
            }
        }
    }
}
