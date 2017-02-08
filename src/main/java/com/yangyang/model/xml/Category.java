package com.yangyang.model.xml;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by syy on 2017/1/22.
 */
public class Category {

    private Integer id;
    private String categoryName;

    private Set<Item> items = new HashSet<>();

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName, Set<Item> items) {
        this.categoryName = categoryName;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
