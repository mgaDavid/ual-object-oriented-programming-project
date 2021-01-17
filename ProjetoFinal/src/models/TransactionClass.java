package models;

import views.CLI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionClass implements Transaction, Serializable {
    protected final int id;
    protected final ClientClass client;
    protected final LocalClass local;
    protected final List<EmployeeClass> employees;
    private final List<TransactedItemClass> items;

    public TransactionClass(int id, ClientClass client, LocalClass local, List<EmployeeClass> employees,
                            List<TransactedItemClass> items) {
        this.id = id;
        this.client = client;
        this.local = local;
        this.employees = employees;
        this.items = items;
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

    public List<EmployeeClass> getEmployees() {
        return employees;
    }

    public List<TransactedItemClass> getItems() {
        return items;
    }

    public void sortItems() {
        this.items.sort(Comparator.comparingInt(item -> item.getItem().getId()));
    }

    public void sortEmployees() {
        this.employees.sort(
                Comparator.comparingInt((EmployeeClass employee) -> employee.getPermission().getOrder()).thenComparing(
                        PersonClass::getName));
    }

    public EmployeeClass getDriver() {
        for (final var employee : this.getEmployees()) {
            if (employee.getCategory().equals(CLI.getCategory("Condutor"))) {
                return employee;
            }
        }
        return null;
    }

    public List<EmployeeClass> getLoaders() {
        final var employees = new ArrayList<EmployeeClass>();
        for (final var employee : this.getEmployees()) {
            if (employee.getCategory().equals(CLI.getCategory("Carregador"))) {
                employees.add(employee);
            }
        }
        return employees;
    }
}
