package com.example.erp.controller;
//import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.services.CoursesService;
import com.example.erp.services.StudentService;
import com.example.erp.utils.SessionUtil;
import com.example.erp.utils.dt2util;
import com.example.erp.utils.dt3util;
import com.example.erp.utils.dtutil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Path("students")
public class StudentsController {

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Students student) throws URISyntaxException {
        System.out.println(student.getEmail());

        StudentService ss_obj= new StudentService();

        int ret=ss_obj.login(student);


//        Session session = SessionUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        String hql="select email from Students  where email= :param";
//        Query query=session.createQuery(hql);
//        query.setParameter("param",mail);
//        List results= query.list();
//        for(int i=0;i<results.size();i++){
//            System.out.println(results.get(i));
//        }
        if(ret==0){
            return Response.status(406).build();
        }
////        student.setGraduation_year("2022/06/06");
////        student.setTotal_credits(10);
////        student.setFirst_name("new");
////        student.setLast_name("newy");
////        student.setEmail(student.getEmail());
////        student.setRoll_no(student.getRoll_no());
////        session.save(student);
////        transaction.commit();
//        session.close();
        return Response.ok().status(200).build();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getdata(@QueryParam("email_id") String email){
        System.out.println("Working |||||||"+email);
//        return Response.ok().build();
//        System.out.println(student.getRoll_no());
//        System.out.println(student.getEmail());
//        System.out.println(student.getStudent_id());
        //Database->
        Calendar rightnow=Calendar.getInstance();
        int year = rightnow.get(Calendar.YEAR);
        int month = rightnow.get(Calendar.MONTH);
        int term=1;
        if(month>=1 && month<=6) term=2;
        else
            term=1;
//        System.out.println(year+" "+term);
        CoursesService cs=new CoursesService();
        HashMap<String,List<String>> preq=cs.getpreq(term,year);
        HashMap<String, dtutil> fac=cs.getcourse_faculty(term,year);
        String mail=email;
        StudentService ss_obj= new StudentService();
        List<String> done=ss_obj.donecourses(email);
        dt2util obj=new dt2util();
        obj.setFac(fac);
        obj.setPreq(preq);
        obj.setDone(done);
        return Response.ok().entity(obj).build();

    }
    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(dt3util obj) throws Exception
    {
        StudentService ss_obj= new StudentService();
        ss_obj.update(obj);
        //System.out.println(obj.getCredits());
        return Response.ok().build();
    }
}
