package models;

import java.io.Serializable;

public class CategoryClass implements Serializable {
    private final String categoryName;
    private final String description;

    public CategoryClass(String category, String description) {
        this.categoryName = category;
        this.description = description;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(CategoryClass category) {
        return this.getCategoryName().equals(category.getCategoryName());
    }
}
