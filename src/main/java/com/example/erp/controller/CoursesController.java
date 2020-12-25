package com.example.erp.controller;
import com.example.erp.bean.Courses;
import com.example.erp.services.CoursesService;
import com.example.erp.services.StudentService;
import com.example.erp.utils.SessionUtil;
import com.example.erp.utils.dt2util;
import com.example.erp.utils.dtutil;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
@Path("courses")
public class CoursesController {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses() {
        List<Courses> courses = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int term;
        if(month>=1 && month<=6) term=2;
        else
            term=1;
        System.out.println("/get working");
        CoursesService cs=new CoursesService();

        HashMap<String,List<String>> preq=cs.getpreq(term,year);
        HashMap<String, dtutil> fac=cs.getcourse_faculty(term,year);

        dt2util obj=new dt2util();
        obj.setFac(fac);
        obj.setPreq(preq);
//        String json=Response.ok().entity(preq).build()+Response.ok().entity(fac).build();
        return Response.ok().entity(obj).build();
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerCourse(@FormDataParam("name") String name,
                                   @FormDataParam("description") String description,
                                   @FormDataParam("credits") Integer credits) throws URISyntaxException {
        System.out.println(name);
        System.out.println(description);
        System.out.println(credits);
        return Response.ok().build();

    }
}
