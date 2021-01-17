package models;

import java.io.Serializable;

public class TransactedItemClass implements Serializable {
    private final ItemClass item;
    private final int transactedQuantity;

    public TransactedItemClass(ItemClass item, int quantity) {
        this.item = item;
        this.transactedQuantity = quantity;
    }

    public ItemClass getItem() {
        return this.item;
    }

    public int getTransactedQuantity() {
        return this.transactedQuantity;
    }
}
