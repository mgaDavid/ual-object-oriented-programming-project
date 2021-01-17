package models;

import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ClientClass extends PersonClass implements Client{
    private final int id;
    public final EmployeeClass manager;
    private final List<ItemClass> items = new ArrayList<>();
    private final List<TransactionClass> deposits = new ArrayList<>();
    private final List<TransactionClass> deliveries = new ArrayList<>();

    public ClientClass(int id, String name, EmployeeClass manager) {
        super(name);
        this.id = id;
        this.manager = manager;
    }

    public int getId() {
        return this.id;
    }

    public EmployeeClass getManager() {
        return this.manager;
    }

    public List<ItemClass> getItems() {
        return items;
    }

    public ItemClass getItem(int id) throws ItemNotFoundException {
        for (ItemClass item : this.getItems()) {
            if (item.getId() == id) {
                return item;
            }
        }

        throw new ItemNotFoundException();
    }

    public int getLastItemId() {
        return this.getItems().size();
    }

    public void addItem(ItemClass item) {
        this.items.add(item);
    }

    public List<TransactionClass> getDeposits() {
        return this.deposits;
    }

    public int getLastDepositId() {
        return this.getDeposits().size();
    }

    public void addDeposit(TransactionClass deposit) {
        this.deposits.add(deposit);
        for (final var transactedItem: deposit.getItems()) {
            final var item = transactedItem.getItem();
            item.deposit(transactedItem.getTransactedQuantity());
        }
    }

    public List<TransactionClass> getDeliveries() {
        return this.deliveries;
    }

    public TransactionClass getDelivery(int id) throws DeliveryNotFoundException {
        for (final var delivery : this.getDeliveries()) {
            if (delivery.getId() == id) {
                return delivery;
            }
        }
        throw new DeliveryNotFoundException();
    }

    public int getLastDeliveryId() {
        return this.getDeliveries().size();
    }

    public void addDelivery(TransactionClass delivery) {
        this.deliveries.add(delivery);
        for (final var transactedItem: delivery.getItems()) {
            final var item = transactedItem.getItem();
            item.remove(transactedItem.getTransactedQuantity());
        }
    }

    public void sortItems(){
        this.items.sort((item1, item2) -> {
            int quantityCmp = Integer.compare(item2.getQuantity(), item1.getQuantity());
            if (quantityCmp != 0) {
                return quantityCmp;
            }
            return Integer.compare(item1.getId(), item2.getId());
        });
    }
}
