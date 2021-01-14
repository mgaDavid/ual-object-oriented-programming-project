package models;

import java.util.List;

public class DepositClass extends TransactionClass {
    private final List<DepositingItemClass> items;
    private final int id;

    public DepositClass(ClientClass client, LocalClass local, List<EmployeeClass> employees,
                        List<DepositingItemClass> items) {
        super(client, local, employees);
        this.items = items;
        this.id = client.addOneDeposit();
    }

    public List<DepositingItemClass> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }
}
