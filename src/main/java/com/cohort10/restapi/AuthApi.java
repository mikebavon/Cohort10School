package com.cohort10.restapi;

import com.cohort10.bean.AuthBeanI;
import com.cohort10.model.User;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/api/auth")
public class AuthApi {

    @EJB
    AuthBeanI authBean;

    @POST
    @Path("/login")
    public Response login(@PathParam("username") String username,@PathParam("password") String password) {

        User user = new User(); //authBean.login(username, password);

        return Response.status(200).entity(user).build();

    }
}
