package com.example.erp.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    @Column(nullable = false, unique = true)
    private String roll_no;
    @Column(nullable = false)
    private String first_name;
    private String last_name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private int total_credits;


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "Student_Courses", joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns = {@JoinColumn(name = "course_id")})
////    private List<Courses> courses;

    public Students() {
    }

    public Students(String email, String roll_no) {
        this.roll_no=roll_no;
        this.email = email;

    }
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_courses",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Courses> courses;
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }
    public  String getRoll_no(){return roll_no;}
    public void setRoll_no(String roll_no){
        this.roll_no=roll_no;
    }

    public int getTotal_credits() {
        return total_credits;
    }

    public void setTotal_credits(int total_credits) {
        this.total_credits = total_credits;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
}
