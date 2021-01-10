package models;

import java.io.Serializable;
import java.util.ArrayList;

public class TransactionClass implements Serializable {
    protected final int id;
    protected final ClientClass client;
    protected final LocalClass local;
    protected final ArrayList<EmployeeClass> employees;
    protected final ItemClass item;
    protected final int quantity;

    public TransactionClass(int id, ClientClass client, LocalClass local, ArrayList<EmployeeClass> employees,
                            ItemClass item, int quantity) {
        this.id = id;
        this.client = client;
        this.local = local;
        this.employees = employees;
        this.item = item;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public ClientClass getClient() {
        return client;
    }

    public LocalClass getLocal() {
        return local;
    }

    public ArrayList<EmployeeClass> getEmployees() {
        return employees;
    }

    public ItemClass getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
