package com.yangyang.model.subClass;

public class Student extends Person {
    private String school;

    public Student() {
    }

    public Student(String school) {
        this.school = school;
    }

    public Student(String name, Integer age, String school) {
        super(name, age);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
