package com.yangyang.model.xml;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by syy on 2017/1/22.
 */
public class Item {
    private Integer id;
    private String itemName;

    private Set<Category> categories = new HashSet<>();

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
