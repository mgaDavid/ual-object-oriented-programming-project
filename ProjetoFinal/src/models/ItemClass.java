package models;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemClass implements Serializable {
    private final int id;
    private final String name;
    private final ClientClass client;
    private final ArrayList<String> permissions;
    private int quantity;

    public ItemClass(int id, String name, ClientClass client, ArrayList<String> permissions) {
        this.id = id;
        this.name = name;
        this.client = client;
        this.permissions = permissions;
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
}
