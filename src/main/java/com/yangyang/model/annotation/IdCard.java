package com.yangyang.model.annotation;

import javax.persistence.*;

@Entity
@Table(name = "t_idcard")
public class IdCard {
    private Integer id;
    private String number;
    private Person person;

    public IdCard() {
    }

    public IdCard(String number) {
        this.number = number;
    }

    public IdCard(String number, Person person) {
        this.number = number;
        this.person = person;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
