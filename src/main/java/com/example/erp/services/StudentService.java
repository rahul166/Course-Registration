
package com.example.erp.services;

import com.example.erp.bean.Students;
import com.example.erp.dao.impl.StudentsDaoImpl;
import com.example.erp.utils.dt3util;

import java.util.List;

public class StudentService {
    public int login(Students student){
        StudentsDaoImpl obj= new StudentsDaoImpl();
        int ret=obj.login(student);
        return ret;
    }
    public List<String> donecourses(String email){
        StudentsDaoImpl obj=new StudentsDaoImpl();
        List<String> res=obj.donecourses(email);
        return res;
    }
    public void  update(dt3util obj){
        StudentsDaoImpl obj1=new StudentsDaoImpl();
        obj1.update(obj);
    }
}
