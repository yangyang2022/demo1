package com.yangyang.model.hqlDemo2;

public class Employee {

    private Integer id;
    private String name;
    private float salary;
    private String email;
    private Department department;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(String name, float salary, String email) {
        this.name = name;
        this.salary = salary;
        this.email = email;
    }

    public Employee(String name, float salary, String email, Department department) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.department = department;
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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                '}';
    }
}
