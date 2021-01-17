package models;

import java.util.List;

public interface Transaction {

    int getId();

    ClientClass getClient();

    LocalClass getLocal();

    List<EmployeeClass> getEmployees();

    List<TransactedItemClass> getItems();

    void sortItems();

    void sortEmployees();

    EmployeeClass getDriver();

    List<EmployeeClass> getLoaders();

}
