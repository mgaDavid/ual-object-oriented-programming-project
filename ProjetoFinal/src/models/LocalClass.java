package models;

import java.io.Serializable;

public class LocalClass implements Local, Serializable {
    private final int id;
    private final String name;

    public LocalClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
