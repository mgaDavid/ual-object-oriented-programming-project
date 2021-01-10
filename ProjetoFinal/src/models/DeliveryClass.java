package models;

import java.util.ArrayList;

public class DeliveryClass extends TransactionClass {
    public final ArrayList<DeliveringItemClass> items;

    public DeliveryClass(int id, ClientClass client, LocalClass local, ArrayList<EmployeeClass> employees,
                         ArrayList<DeliveringItemClass> items) {
        super(id, client, local, employees);
        this.items = items;
    }

    public ArrayList<DeliveringItemClass> getItems() {
        return items;
    }
}
