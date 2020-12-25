package com.example.erp.services;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.dao.impl.CoursesDaoImpl;
import com.example.erp.dao.impl.StudentsDaoImpl;
import com.example.erp.utils.dtutil;

import java.util.HashMap;
import java.util.List;

public class CoursesService {
   public HashMap<String, List<String>> getpreq(int term,int year){
       CoursesDaoImpl obj=new CoursesDaoImpl();
       return obj.getcourses_preq(term,year);
   }
   public HashMap<String, dtutil> getcourse_faculty(int term,int year){
       CoursesDaoImpl obj=new CoursesDaoImpl();
       return obj.getcourses(term,year);
   }
}
