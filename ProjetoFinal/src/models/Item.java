package models;

import java.util.List;

public interface Item {

    int getId();

    String getName();

    ClientClass getClient();

    List<PermissionClass> getPermissions();

    int getQuantity();

    void deposit(int quantity);

    void remove(int quantity);

    String getPermissionsInitials();

    String getPermissionsNames();

    void sortPermissions();

}
