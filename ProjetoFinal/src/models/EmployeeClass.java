package models;

import exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeClass extends PersonClass {
    private static int classCounter = 0;
    private final static List<String> permissions = new ArrayList<>(Arrays.asList("N", "S", "P"));
    private final static List<String> categories = new ArrayList<>(
            Arrays.asList("Carregador", "Condutor", "Gestor"));
    private final int id;
    private final String category;
    private final String permission;

    public EmployeeClass(String name, String category, String permission, ArrayList<EmployeeClass> existingEmployees)
            throws NonexistentCategoryException, ExistingEmployeeException, NonexistentPermissionException,
            InvalidInstructionException {
        super(name);

        if (!existCategory(category)) {
            throw new NonexistentCategoryException();
        }

        if (!existPermission(permission)) {
            throw new InvalidInstructionException();
        }

        if ((category.equals("Condutor") && permission.equals("S")) ||
            (category.equals("Carregador") && permission.equals("P")) ||
            (category.equals("Gestor") && !permission.equals("N"))
        ) {
            throw new NonexistentPermissionException();
        }

        for (EmployeeClass employee : existingEmployees) {
            if (employee.getName().equals(name)) {
                throw new ExistingEmployeeException();
            }
        }

        this.category = category;
        this.permission = permission;
        this.id = ++EmployeeClass.classCounter;
    }

    public int getId() {
        return this.id;
    }

    public String getName() { return this.name; }

    public String getCategory() {
        return this.category;
    }

    public String getPermission() {
        return this.permission;
    }

    public static int getClassCounter() {
        return EmployeeClass.classCounter;
    }

    public static List<String> getPermissions() {
        return permissions;
    }

    public static List<String> getCategories() {
        return categories;
    }

    public static boolean existPermission(String permission) {
        return getPermissions().contains(permission);
    }

    public static boolean existCategory(String category) {
        return getCategories().contains(category);
    }
}
