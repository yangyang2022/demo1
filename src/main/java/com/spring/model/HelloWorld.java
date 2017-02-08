package com.spring.model;

public class HelloWorld {

    private Integer age;
    private String name;

    public HelloWorld() {
    }

    public HelloWorld(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HelloWorld{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
