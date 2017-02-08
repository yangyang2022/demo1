package com.yangyang.model.annotation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    private Integer id;
    private String name;
    private Set<Admin> admins = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id, String name, Set<Admin> admins) {
        this.id = id;
        this.name = name;
        this.admins = admins;
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

    @ManyToMany
    @JoinTable(name = "t_role_admin",
            joinColumns = {@JoinColumn(name = "r_id")},
            inverseJoinColumns = {@JoinColumn(name = "a_id")})
    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }
}
