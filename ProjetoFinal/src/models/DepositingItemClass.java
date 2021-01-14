package models;

import exceptions.QuantityInsufficientException;

public class DepositingItemClass {
    public final ItemClass item;
    public final int quantity;

    public DepositingItemClass(ItemClass item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemClass getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
