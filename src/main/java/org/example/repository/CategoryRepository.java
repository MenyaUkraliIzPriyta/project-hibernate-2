package org.example.repository;


import org.example.entity.Category;

public class CategoryRepository extends FoundationReposirory<Category> {
    public CategoryRepository() {
        super(Category.class);
    }
}
