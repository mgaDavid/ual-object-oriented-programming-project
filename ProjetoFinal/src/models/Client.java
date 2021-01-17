package models;

import exceptions.DeliveryNotFoundException;
import exceptions.ItemNotFoundException;

import java.util.List;

public interface Client {

    int getId();

    EmployeeClass getManager();

    List<ItemClass> getItems();

    ItemClass getItem(int id) throws ItemNotFoundException;

    int getLastItemId();

    void addItem(ItemClass item);

    List<TransactionClass> getDeposits();

    int getLastDepositId();

    void addDeposit(TransactionClass deposit);

    List<TransactionClass> getDeliveries();

    TransactionClass getDelivery(int id) throws DeliveryNotFoundException;

    int getLastDeliveryId();

    void addDelivery(TransactionClass delivery);

    void sortItems();

}
