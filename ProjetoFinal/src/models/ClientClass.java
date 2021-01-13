package models;

import exceptions.*;

import java.util.ArrayList;

public class ClientClass extends PersonClass {
    private static int classCounter;
    private final int id;
    private final int employeeId;
    private int lastItem;
    private ArrayList<ItemClass> items = new ArrayList<>();
    private int lastDeposit;
    private ArrayList<DepositClass> deposits = new ArrayList<>();
    private int lastDelivery;
    private ArrayList<DeliveryClass> deliveries = new ArrayList<>();

    public ClientClass(String name, int employeeId, ArrayList<ClientClass> existingClients) throws ExistingClientException {
        super(name);

        for (ClientClass client : existingClients) {
            if (client.getName().equals(name)) {
                throw new ExistingClientException();
            }
        }
        this.employeeId = employeeId;
        this.id = ++ClientClass.classCounter;
    }

    public int getId() {
        return this.id;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public static int getClassCounter() {
        return ClientClass.classCounter;
    }

    public ArrayList<ItemClass> getItems() {
        return items;
    }

    public int getLastItem() {
        return lastItem;
    }

    public int addOneItem() {
        return ++this.lastItem;
    }

    public void addItem(String itemName, ArrayList<String> permissions) throws NonexistentPermissionException {
        this.items.add(new ItemClass(itemName, this, permissions));
    }

    private int addOneDeposit() {
        return ++this.lastDeposit;
    }

    public void addDeposit(int depositId, LocalClass local, ArrayList<EmployeeClass> employees, ArrayList<DepositingItemClass> items,
                           int quantity) {

        this.deposits.add(new DepositClass(this.addOneDeposit(), this, local, employees, items));
    }

    private int addOneDelivery() {
        return ++this.lastDelivery;
    }

    public void addDelivery(int deliveryId, LocalClass local, ArrayList<EmployeeClass> employees, ArrayList<DeliveringItemClass> items,
                            int quantity) {
        this.deliveries.add(new DeliveryClass(this.addOneDelivery(), this, local, employees, items));
    }
    
    public ItemClass getItem(int id) throws ItemNotFoundException {
        for (ItemClass item : this.getItems()) {
            if (item.getId() == id) {
                return item;
            }
        }

        throw new ItemNotFoundException();
    }
}
