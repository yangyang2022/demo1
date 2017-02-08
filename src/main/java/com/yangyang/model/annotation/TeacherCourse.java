package com.yangyang.model.annotation;

import javax.persistence.*;

@Entity
@Table(name = "t_teacher_course")
public class TeacherCourse {
    private Integer id;
    private double ach;
    private Teacher teacher;
    private Course course;

    public TeacherCourse() {
    }

    public TeacherCourse(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }

    public TeacherCourse(double ach, Teacher teacher, Course course) {
        this.ach = ach;
        this.teacher = teacher;
        this.course = course;
    }


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAch() {
        return ach;
    }

    public void setAch(double ach) {
        this.ach = ach;
    }

    @ManyToOne
    @JoinColumn(name = "t_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    @JoinColumn(name = "c_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
