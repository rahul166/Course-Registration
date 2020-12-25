package com.example.erp.dao;

import com.example.erp.utils.dtutil;

import java.util.HashMap;
import java.util.List;

public interface CoursesDao {
    public abstract HashMap<String, List<String>> getcourses_preq(int term, int year);
    public abstract HashMap<String , dtutil> getcourses(int term,int year);
}
