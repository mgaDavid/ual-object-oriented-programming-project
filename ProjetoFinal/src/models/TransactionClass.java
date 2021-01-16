package models;

import java.io.Serializable;
import java.util.List;

public class TransactionClass implements Serializable {
    protected final ClientClass client;
    protected final LocalClass local;
    protected final List<EmployeeClass> employees;

    protected TransactionClass(ClientClass client, LocalClass local, List<EmployeeClass> employees) {
        this.client = client;
        this.local = local;
        this.employees = employees;
    }

    protected ClientClass getClient() {
        return this.client;
    }

    protected LocalClass getLocal() {
        return this.local;
    }

    protected List<EmployeeClass> getEmployees() {
        return this.employees;
    }
}
