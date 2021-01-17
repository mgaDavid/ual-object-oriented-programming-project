package models;

import java.io.Serializable;

public class PermissionClass implements Serializable {
    private final int order;
    private final String initials;
    private final String name;
    private final String description;

    public PermissionClass(int order, String initials, String name, String description) {
        this.order = order;
        this.initials = initials;
        this.name = name;
        this.description = description;
    }

    public int getOrder() {
        return this.order;
    }

    public String getInitials() {
        return this.initials;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
