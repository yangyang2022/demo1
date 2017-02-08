package com.yangyang.model.annotation;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_course")
public class Course {
    private Integer id;
    private String name;
    private Set<TeacherCourse> tcs = new HashSet<>();

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Set<TeacherCourse> tcs) {
        this.name = name;
        this.tcs = tcs;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "course")
    @LazyCollection(LazyCollectionOption.EXTRA)
    public Set<TeacherCourse> getTcs() {
        return tcs;
    }

    public void setTcs(Set<TeacherCourse> tcs) {
        this.tcs = tcs;
    }
}
