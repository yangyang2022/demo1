package com.yangyang.model.HQLDemo1;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity@Table(name = "t_spe")
public class Special {

    private Integer id;
    private String name;
    private String type;
    private Set<ClassRoom> classRooms = new HashSet<>();

    public Special() {
    }

    public Special(Integer id) {
        this.id = id;
    }

    public Special(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "special")
    public Set<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(Set<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }
}
