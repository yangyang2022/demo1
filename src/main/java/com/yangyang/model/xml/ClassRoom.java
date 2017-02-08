package com.yangyang.model.xml;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_classroom")
public class ClassRoom {
    private Integer id;
    private String clzName;

    private Set<Student> students = new HashSet<>();

    public ClassRoom() {
    }

    public ClassRoom(String clzName) {
        this.clzName = clzName;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "clz_name")
    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }

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
                ", clzName='" + clzName + '\'' +
                '}';
    }
}
