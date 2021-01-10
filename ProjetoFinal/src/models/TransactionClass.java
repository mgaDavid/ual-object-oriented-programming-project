package models;

import java.io.Serializable;
import java.util.ArrayList;

public class TransactionClass implements Serializable {
    protected final int id;
    protected final ClientClass client;
    protected final LocalClass local;
    protected final ArrayList<EmployeeClass> employees;

    protected TransactionClass(int id, ClientClass client, LocalClass local, ArrayList<EmployeeClass> employees) {
        this.id = id;
        this.client = client;
        this.local = local;
        this.employees = employees;
    }

    protected int getId() {
        return this.id;
    }

    protected ClientClass getClient() {
        return this.client;
    }

    protected LocalClass getLocal() {
        return this.local;
    }

    protected ArrayList<EmployeeClass> getEmployees() {
        return this.employees;
    }
}
