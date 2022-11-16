package com.cohort10.restapi;

import com.cohort10.bean.StudentBeanI;
import com.cohort10.model.Student;
import com.cohort10.rest.BaseRestApi;
import com.cohort10.rest.ResponseWrapper;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentRestApi extends BaseRestApi {

    @EJB
    private StudentBeanI studentBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Student student) {
        try {
            studentBean.add(student);
            return Response.status(Response.Status.OK).entity(new ResponseWrapper()).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ResponseWrapper(false, ex.getMessage())).build();
        }

    }

    @RolesAllowed("LOGGED_IN")
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("id") Long id,
             @QueryParam("systech")  String nameOfCompany) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>The id is " + id);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> company name " + nameOfCompany);
        return Response.status(Response.Status.OK).entity(studentBean.list()).build();
    }


}
