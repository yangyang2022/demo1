package com.yangyang.model.xml;

public class Order {

    private Integer order_id;
    private String order_name;
    private Customer customer;

    public Order() {
    }

    public Order(Integer order_id) {
        this.order_id = order_id;
    }

    public Order(String order_name, Customer customer) {
        this.order_name = order_name;
        this.customer = customer;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return order_id != null ? order_id.equals(order.order_id) : order.order_id == null;
    }

    @Override
    public int hashCode() {
        return order_id != null ? order_id.hashCode() : 0;
    }
}
