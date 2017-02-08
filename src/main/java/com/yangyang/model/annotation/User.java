package com.yangyang.model.annotation;

import com.yangyang.annotation.Past;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_user")
@DynamicUpdate
public class User {
    private Integer id;
    private String username;
    private String password;
    private LocalDate born;

    public User() {
    }

    public User(String username, String password, LocalDate born) {
        this.username = username;
        this.password = password;
        this.born = born;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", born=" + born +
                '}';
    }
}
