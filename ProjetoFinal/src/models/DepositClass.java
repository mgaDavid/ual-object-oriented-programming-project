package models;

import java.util.ArrayList;

public class DepositClass extends TransactionClass {
    public final ArrayList<DepositingItemClass> items;

    public DepositClass(int id, ClientClass client, LocalClass local, ArrayList<EmployeeClass> employees,
                        ArrayList<DepositingItemClass> items) {
        super(id, client, local, employees);
        this.items = items;
    }

    public ArrayList<DepositingItemClass> getItems() {
        return items;
    }
}
