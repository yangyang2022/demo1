package com.yangyang.model.DTO;

public class StudentDto {
    private Integer id;
    private String sname;
    private String ssex;
    private String sclzName;
    private String sspecialName;

    public StudentDto() {
    }

    public StudentDto(Integer id, String sname, String ssex, String sclzName, String sspecialName) {
        this.id = id;
        this.sname = sname;
        this.ssex = ssex;
        this.sclzName = sclzName;
        this.sspecialName = sspecialName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSclzName() {
        return sclzName;
    }

    public void setSclzName(String sclzName) {
        this.sclzName = sclzName;
    }

    public String getSspecialName() {
        return sspecialName;
    }

    public void setSspecialName(String sspecialName) {
        this.sspecialName = sspecialName;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sclzName='" + sclzName + '\'' +
                ", sspecialName='" + sspecialName + '\'' +
                '}';
    }
}
