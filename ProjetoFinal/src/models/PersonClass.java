package models;

import java.io.Serializable;

public class PersonClass implements Serializable {
    protected final String name;

    public PersonClass(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

