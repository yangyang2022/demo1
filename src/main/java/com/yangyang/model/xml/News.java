package com.yangyang.model.xml;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "NEWS")
@DynamicUpdate
public class News {

    private Integer id;

    private String title;

    private String author;

    private LocalDate data;

    public News() {
    }

    public News(String title, String author, LocalDate data) {
        this.title = title;
        this.author = author;
        this.data = data;
    }

    public News(Integer id, String title, String author, LocalDate data) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.data = data;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", data=" + data +
                '}';
    }
}
