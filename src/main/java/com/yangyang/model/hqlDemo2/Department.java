package com.yangyang.model.hqlDemo2;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private Integer id;
    private String name;
    private Set<Employee> employees = new HashSet<>();

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }

    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                '}';
    }
}
