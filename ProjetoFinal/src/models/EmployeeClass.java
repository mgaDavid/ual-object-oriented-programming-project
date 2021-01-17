package models;

public class EmployeeClass extends PersonClass {
    protected final int id;
    protected final PermissionClass permission;
    protected final CategoryClass category;

    public EmployeeClass(int id, String name, PermissionClass permission, CategoryClass category) {
        super(name);
        this.id = id;
        this.permission = permission;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public PermissionClass getPermission() {
        return this.permission;
    }

    public CategoryClass getCategory() {
        return this.category;
    }
}
