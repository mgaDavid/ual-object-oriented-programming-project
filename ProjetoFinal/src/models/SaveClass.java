package models;

import java.io.Serializable;
import java.util.List;

public class SaveClass implements Save, Serializable {
    private final List<EmployeeClass> employees;
    private final List<ClientClass> clients;
    private final List<LocalClass> locals;

    public SaveClass(List<EmployeeClass> employees, List<ClientClass> clients, List<LocalClass> locals) {
        this.employees = employees;
        this.clients = clients;
        this.locals = locals;
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
}
