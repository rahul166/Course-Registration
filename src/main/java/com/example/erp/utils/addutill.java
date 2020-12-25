package com.example.erp.utils;
import com.example.erp.bean.Courses;

import com.example.erp.bean.Employees;
import com.example.erp.bean.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class addutill {
        public static void main(String[] args) {
                Session session = SessionUtil.getSession();
                Transaction transaction = session.beginTransaction();
                Courses cr=new Courses();
                Employees emp=new Employees();
                Students s=new Students();
//                List<Courses> query=session.createQuery("From Courses ").getResultList();
//                for (int i = 0; i < query.size(); i++) {
//                        System.out.println(query.get(i).getName());
//                }

                emp.setFirst_name("xyz");
                emp.setLast_name("abc");
                session.save(emp);
                cr.setName("ss");
                cr.setYear(2);
                cr.setTerm(3);
                cr.setCredits(4);
                cr.setCapacity(100);
                cr.setCourse_code("101");
                cr.setEmployees(emp);
                session.save(cr);

                Courses cr2=new Courses();
                cr2.setName("spe");
                cr2.setYear(2);
                cr2.setTerm(3);
                cr2.setCredits(4);
                cr2.setCapacity(100);
                cr2.setCourse_code("102");
                List<Courses> l=new ArrayList<>();
                l.add(cr);
                cr2.setEmployees(emp);
                cr2.setCourses(l);
                session.save(cr2);
                s.setEmail("abc@example.com");
                s.setFirst_name("xyz");
                s.setLast_name("abc");
                s.setRoll_no("MT2020016");
                s.setTotal_credits(6);
                List<Courses> taken=new ArrayList<Courses>();
                taken.add(cr);
                taken.add(cr2);
                s.setCourses(taken);
                session.save(s);

//                        session.save(student);
        transaction.commit();
        session.close();

        }


}
