package com.cohort10.restapi;

import com.cohort10.bean.AuthBeanI;
import com.cohort10.model.Auth;
import com.cohort10.model.User;
import com.cohort10.rest.ResponseWrapper;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthApi {

    @EJB
    AuthBeanI authBean;

    @PermitAll
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RestLoginWrapper loginWrapper) {

        Auth auth = new Auth();
        auth.setUsername(loginWrapper.getUsername());
        auth.setPassword(loginWrapper.getPassword());
        try {
            User user = authBean.login(auth);
            return Response.status(Response.Status.OK)
                .entity(new ResponseWrapper("authorized", user.getBearerToken())).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ResponseWrapper(false, ex.getMessage())).build();
        }



    }
}
