package models;

import java.util.ArrayList;

public class ClientClass extends PersonClass {
    private static int classCounter;
    private final int id;
    private int lastItem;
    private ArrayList<ItemClass> items;
    private int lastDeposit;
    private ArrayList<DepositClass> deposits;
    private int lastDelivery;
    private ArrayList<DeliveryClass> deliveries;

    public ClientClass(String name) {
        super(name);
        this.id = ++ClientClass.classCounter;
    }

    public int getId() {
        return this.id;
    }

    public static int getClassCounter() {
        return ClientClass.classCounter;
    }

    public int getLastItem() {
        return lastItem;
    }

    private int AddOneItem() {
        return ++this.lastItem;
    }

    public void addItem(String itemName, ArrayList<String> permissions) {
        items.add(new ItemClass(this.AddOneItem(), itemName, this, permissions));
    }

    private int AddOneDeposit() {
        return ++this.lastDeposit;
    }

    public void addDeposit(int depositId, LocalClass local, ArrayList<EmployeeClass> employees, ItemClass item,
                           int quantity) {

        deposits.add(new DepositClass(this.AddOneDeposit(), this, local, employees, item, quantity));
    }

    private int AddOneDelivery() {
        return ++this.lastDelivery;
    }

    public void addDelivery(int deliveryId, LocalClass local, ArrayList<EmployeeClass> employees, ItemClass item,
                            int quantity) {
        deliveries.add(new DeliveryClass(this.AddOneDelivery(), this, local, employees, item, quantity));
    }
}
