package models;

import java.util.ArrayList;
import java.util.List;

public class DeliveryClass extends TransactionClass {
    private final List<DeliveringItemClass> items;
    private final int id;

    public DeliveryClass(ClientClass client, LocalClass local, List<EmployeeClass> employees,
                         List<DeliveringItemClass> items) {
        super(client, local, employees);
        this.items = items;
        this.id = getClient().addOneDelivery();
    }

    public List<DeliveringItemClass> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }
}
