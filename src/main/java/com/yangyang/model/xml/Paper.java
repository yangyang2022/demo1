package com.yangyang.model.xml;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Paper {

    private Integer id;
    private String title;
    private String content;
    private String desc;
    private String author;
    private LocalDateTime data;
    private Blob pic;

    public Paper() {
    }

    public Paper(String title, String content, String desc, String author, LocalDateTime data) {
        this.title = title;
        this.content = content;
        this.desc = desc;
        this.author = author;
        this.data = data;
    }

    public Paper(String title, String content, String desc, String author, LocalDateTime data, Blob pic) {
        this.title = title;
        this.content = content;
        this.desc = desc;
        this.author = author;
        this.data = data;
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Blob getPic() {
        return pic;
    }

    public void setPic(Blob pic) {
        this.pic = pic;
    }
}
