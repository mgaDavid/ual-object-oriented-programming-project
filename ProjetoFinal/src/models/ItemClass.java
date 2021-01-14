package models;


import exceptions.NonexistentPermissionException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemClass implements Serializable {
    private final int id;
    private final String name;
    private final ClientClass client;
    private final ArrayList<String> permissions;
    private int quantity;

    public ItemClass(String name, ClientClass client, List<String> permissions)
            throws NonexistentPermissionException {

        for (String permission : permissions) {
            if (!EmployeeClass.existPermission(permission)) {
                throw new NonexistentPermissionException();
            }
        }
        this.permissions = permissions;
        this.name = name;
        this.client = client;
        this.id = client.addOneItem();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ClientClass getClient() {
        return this.client;
    }

    public ArrayList<String> getPermissions() {
        return this.permissions;
    }

    public void deposit(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void remove(int quantity) {
        this.quantity = this.quantity - quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
