package com.yangyang.model.xml;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private Integer customer_id;
    private String name;

    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
