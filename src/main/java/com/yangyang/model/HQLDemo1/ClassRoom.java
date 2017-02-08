package com.yangyang.model.HQLDemo1;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity@Table(name = "t_clz")
public class ClassRoom {
    private Integer id;
    private String name;
    private Integer grade;
    private Set<Student> students = new HashSet<>();
    private Special special;

    public ClassRoom() {
    }

    public ClassRoom(Integer id) {
        this.id = id;
    }

    public ClassRoom(String name, Integer grade, Special special) {
        this.name = name;
        this.grade = grade;
        this.special = special;
    }

    public ClassRoom(String name, Integer grade, Set<Student> students, Special special) {
        this.name = name;
        this.grade = grade;
        this.students = students;
        this.special = special;
    }

    public ClassRoom(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
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
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Fetch(FetchMode.SUBSELECT)
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToOne
    @JoinColumn(name = "spe_id")
    public Special getSpecial() {
        return special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }
}
