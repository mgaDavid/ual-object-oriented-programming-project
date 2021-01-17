package models;

import java.util.List;

public interface Save {

    List<EmployeeClass> getEmployees();

    List<ClientClass> getClients();

    List<LocalClass> getLocals();

}
