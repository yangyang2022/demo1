package com.yangyang.model.xml;

import javax.persistence.*;

@Entity
@Table(name = "t_student")
public class Student {

    private Integer id;
    private String name;
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, ClassRoom classRoom) {
        this.name = name;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clz_id")
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classRoom=" + classRoom +
                '}';
    }
}
