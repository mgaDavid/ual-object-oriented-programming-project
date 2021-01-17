package models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class ItemClass implements Item, Serializable {
    private final int id;
    private final String name;
    private final ClientClass client;
    private final List<PermissionClass> permissions;
    private int quantity = 0;

    public ItemClass(int id, String name, ClientClass client, List<PermissionClass> permissions) {
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

    public List<PermissionClass> getPermissions() {
        return this.permissions;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void deposit(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void remove(int quantity) {
        this.quantity = this.quantity - quantity;
    }

    public String getPermissionsInitials() {
        StringBuilder strings = new StringBuilder();
        for (int i = 0; i < this.getPermissions().size(); i++) {
            final var initial = this.getPermissions().get(i).getInitials();
            if (i > 0) {
                strings.append(",");
            }
            strings.append(initial);
        }
        return strings.toString();
    }

    public String getPermissionsNames() {
        StringBuilder strings = new StringBuilder();
        for (int i = 0; i < this.getPermissions().size(); i++) {
            final var name = this.getPermissions().get(i).getName();
            if (i > 0) {
                strings.append(",");
            }
            strings.append(name);
        }
        return strings.toString();
    }

    public void sortPermissions(){
        this.permissions.sort(Comparator.comparingInt(PermissionClass::getOrder));
    }
}
