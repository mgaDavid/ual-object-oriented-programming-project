package models;

import java.io.Serializable;

public class LocalClass implements Serializable {
    private static int classCounter;
    private static int id;
    private final String name;

    public LocalClass(String name) {
        this.id = ++LocalClass.classCounter;
        this.name = name;
    }

    public static int getClassCounter() {
        return classCounter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
