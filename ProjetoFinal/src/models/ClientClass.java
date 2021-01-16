package models;

import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ClientClass extends PersonClass {
    private static int classCounter;
    private final int id;
    public final EmployeeClass manager;
    private int lastItem;
    private List<ItemClass> items = new ArrayList<>();
    private int lastDeposit;
    private List<DepositClass> deposits = new ArrayList<>();
    private int lastDelivery;
    private List<DeliveryClass> deliveries = new ArrayList<>();

    public ClientClass(String name, EmployeeClass manager, List<ClientClass> existingClients) throws ExistingClientException {
        super(name);

        for (ClientClass client : existingClients) {
            if (client.getName().equals(name)) {
                throw new ExistingClientException();
            }
        }
        this.manager = manager;
        this.id = ++ClientClass.classCounter;
    }

    public int getId() {
        return this.id;
    }

    public EmployeeClass getManager() {
        return this.manager;
    }

    public static int getClassCounter() {
        return ClientClass.classCounter;
    }

    public List<ItemClass> getItems() {
        return items;
    }

    public int getLastItem() {
        return lastItem;
    }

    public int addOneItem() {
        return ++this.lastItem;
    }

    public void addItem(String itemName, List<String> permissions) throws NonexistentPermissionException {
        this.items.add(new ItemClass(itemName, this, permissions));
    }

    public int addOneDeposit() {
        return ++this.lastDeposit;
    }

    public void addDeposit(LocalClass local, List<EmployeeClass> employees, List<DepositingItemClass> items) {

        this.deposits.add(new DepositClass(this, local, employees, items));
    }

    public int addOneDelivery() {
        return ++this.lastDelivery;
    }

    public void addDelivery(LocalClass local, List<EmployeeClass> employees, List<DeliveringItemClass> items) {
        this.deliveries.add(new DeliveryClass(this, local, employees, items));
    }
    
    public ItemClass getItem(int id) throws ItemNotFoundException {
        for (ItemClass item : this.getItems()) {
            if (item.getId() == id) {
                return item;
            }
        }

        throw new ItemNotFoundException();
    }

    public DepositClass getLastDeposit() {
        List<DepositClass> deposits = this.getDeposits();
        return deposits.get(deposits.size() - 1);
    }

    public List<DepositClass> getDeposits() {
        return this.deposits;
    }
}
