package com.yangyang.model.annotation;

import javax.persistence.*;

@Entity
@Table(name = "t_person")
public class Person {
    private Integer id;
    private String name;
    private IdCard idCard;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, IdCard idCard) {
        this.name = name;
        this.idCard = idCard;
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

    @OneToOne(mappedBy = "person")
    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
}
