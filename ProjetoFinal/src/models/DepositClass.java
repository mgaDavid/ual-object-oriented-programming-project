package models;

import java.util.ArrayList;

public class DepositClass extends TransactionClass {
    public DepositClass(int id, ClientClass client, LocalClass local, ArrayList<EmployeeClass> employees,
                        ItemClass item, int quantity) {
        super(id, client, local, employees, item, quantity);
    }
}
