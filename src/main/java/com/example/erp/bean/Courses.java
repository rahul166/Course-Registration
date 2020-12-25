package com.example.erp.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;

    @Column(nullable = false, unique = true)
    private String course_code;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private int term;
    @Column(nullable = false)
    private int credits;
    @Column(nullable = false)
    private int capacity;
//
//    @ManyToMany
//    @JoinColumn(name="Faculty")
//    private List<Employees> employee;
    @ManyToOne
    @JoinColumn(name="Faculty")
    private Employees employees;
    public Courses(){

    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "course_prerequisite",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "prerequisite") }
    )


    private List<Courses> courses;

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
