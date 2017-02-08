package com.yangyang.model.annotation;

import javax.persistence.*;

@Entity
@Table(name = "t_student")
public class Student {
    private Integer id;
    private String name;
    private Integer number;
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(String name, Integer number, ClassRoom classRoom) {
        this.name = name;
        this.number = number;
        this.classRoom = classRoom;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clz_id")
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
