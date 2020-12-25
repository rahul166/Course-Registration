package com.example.erp.dao.impl;

import com.example.erp.dao.CoursesDao;
import com.example.erp.utils.SessionUtil;
import com.example.erp.utils.dtutil;
import org.hibernate.Session;
import org.hibernate.query.Query;
//import sun.font.CoreMetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class CoursesDaoImpl implements CoursesDao{
//    public static void main(String[] args) {
//        getcourses_preq();
//        getcourses();
//    }
    @Override
    public HashMap<String, List<String>> getcourses_preq(int term,int year) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        String hql = "select cpp.course_code,c2.course_code 'prerequisite' from (select course_code,pid,cp.term,cp.year from (select c.course_id 'cid',p.prerequisite 'pid',c.term,c.year from courses as c left join course_prerequisite as p on c.course_id=p.course_id) as cp,courses where cp.cid=course_id) as cpp,courses as c2 where cpp.term= :term1 and cpp.year= :year1 and cpp.pid=c2.course_id ;";
        Query query = session.createSQLQuery(hql);
        query.setParameter("term1",term);
        query.setParameter("year1",year);
//        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Object[]> results = query.list();
        HashMap<String,List<String>> ans=new HashMap<String,List<String>>();
        for (Object[] aRow : results) {
            String course = (String) aRow[0];
            String preq = (String) aRow[1];
            System.out.println(course + " - " + preq);
            if(ans.containsKey(course))
                ans.get(course).add(preq);
            else
            {   List<String> temp=new ArrayList<String>();
                temp.add(preq);
                ans.put(course,temp);}
        }
        for (HashMap.Entry<String,List<String>> entry : ans.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        return ans;
    }
    @Override
    public HashMap<String, dtutil> getcourses(int term,int year) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        String hql = "select c.course_code,concat(e.first_name,' ',e.last_name) 'Faculty_name',c.capacity,c.credits,c.name from courses as c,employees as e where c.term= :term and c.year= :year and c.Faculty=e.employee_id;";
        Query query = session.createNativeQuery(hql);
        query.setParameter("term",term);
        query.setParameter("year",year);
//        query.setParameter("param",student.getEmail());
//        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Object[]> results = query.list();
        HashMap<String, dtutil> ans=new HashMap<String, dtutil>();
        for (Object[] aRow : results) {
            String course_code = (String) aRow[0];
            String name= (String) aRow[1];
            int capacity = (int) aRow[2];
            int credits = (int) aRow[3];
            String course_name= (String) aRow[4];
            dtutil obj=new dtutil();
            obj.setName(name);
            obj.setCourse_name(course_name);
            obj.setCapacity(capacity);
            obj.setCredits(credits);
            System.out.println(course_code + " - " + obj.getName()+" -capacity-" +obj.getCapacity()+"-credits-"+obj.getCredits());
            ans.put(course_code,obj);
        }
//        for (HashMap.Entry<String,String> entry : ans.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
         return ans;
    }

}

