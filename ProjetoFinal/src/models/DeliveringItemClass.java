package models;

import exceptions.QuantityInsufficientException;

public class DeliveringItemClass {
    public final ItemClass item;
    public final int quantity;

    public DeliveringItemClass(ItemClass item, int quantity) throws QuantityInsufficientException {
        if (item.getQuantity() < quantity) {
            throw new QuantityInsufficientException();
        }
        this.item = item;
        this.quantity = quantity;
    }
}
