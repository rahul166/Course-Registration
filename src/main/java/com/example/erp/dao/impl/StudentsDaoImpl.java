package com.example.erp.dao.impl;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.dao.StudentsDao;
import com.example.erp.utils.SessionUtil;
import com.example.erp.utils.dt3util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentsDao {
    @Override
    public int login(Students student){
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        String hql="select email from Students  where email= :param";
        Query query=session.createQuery(hql);
        query.setParameter("param",student.getEmail());
        List results= query.list();
        for(int i=0;i<results.size();i++){
            System.out.println(results.get(i));
        }
        if(results.size()==0){
            return 0;
        }

        session.close();
        return 1;

    }
    @Override
    public List<String> donecourses(String email){
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        String hql = "select cs.course_code,cs.course_id from (select c.course_id from (select student_id from students where email= :email_id) as s,student_courses as c where s.student_id=c.student_id) as st,courses as cs where st.course_id=cs.course_id;";
        Query query = session.createNativeQuery(hql);
        query.setParameter("email_id",email);
        List<Object[]> results=query.list();
        List<String> ans=new ArrayList<String>();
        for (Object[] aRow : results) {
            String course_code = (String) aRow[0];
            System.out.println(course_code);
            ans.add(course_code);
        }
        session.close();
    return ans;
    }
    @Override
    public void update(dt3util obj) {
        Session session = SessionUtil.getSession();
        Transaction transaction= session.beginTransaction();
        List<String> courses= new ArrayList<String>();
        courses=obj.getCourses();
        int len=courses.size();
        List<Courses> ans=new ArrayList<Courses>();
//
        Query query2=session.createQuery("from Courses e where e.course_code in (:ids)").setParameterList("ids", courses);
        List<Courses> results=query2.list();
        for (Courses aRow : results) {
            Courses course = (Courses) aRow;
            ans.add(course);
        }
        String email=obj.getEmail();
        Query q=session.createQuery("from Students where email= :email_id ").setParameter("email_id",email);
        List<Students> results1=q.list();
        Students student=(Students) results1.get(0);
        System.out.println(student.getEmail());
        student.setCourses(ans);
        student.setTotal_credits(student.getTotal_credits()+obj.getCredits());
        for(Courses aRow : ans)
        {
            aRow.setCapacity(aRow.getCapacity()-1);
            session.save(aRow);
        }

        for(int i=0;i<ans.size();i++)
        {

        }
        session.update(student);
        transaction.commit();
        session.close();
    }
}
