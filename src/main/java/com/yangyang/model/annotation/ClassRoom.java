package com.yangyang.model.annotation;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_classroom")
public class ClassRoom {

    private Integer id;
    private String name;
    private Integer grade;

    private Set<Student> students = new HashSet<>();

    public ClassRoom() {
    }

    public ClassRoom(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
    }

    public ClassRoom(String name, Integer grade, Set<Student> students) {
        this.name = name;
        this.grade = grade;
        this.students = students;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @OneToMany(mappedBy = "classRoom")
    @LazyCollection(LazyCollectionOption.EXTRA) //select count(id) from ...
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", students=" + students +
                '}';
    }
}
