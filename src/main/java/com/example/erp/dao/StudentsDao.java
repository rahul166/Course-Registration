package com.example.erp.dao;

import com.example.erp.bean.Students;
import com.example.erp.utils.dt3util;

import javax.ws.rs.core.Response;
import java.util.List;


public interface StudentsDao{
    public abstract int login(Students student);
    public abstract List<String> donecourses(String email);
    public abstract void update(dt3util obj);
}