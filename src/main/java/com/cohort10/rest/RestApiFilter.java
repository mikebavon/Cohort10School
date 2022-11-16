package com.cohort10.rest;

import com.cohort10.bean.AuthBeanI;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Provider
public class RestApiFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @EJB
    AuthBeanI authBean;

    public void filter(ContainerRequestContext requestContext) throws IOException {

        Method method = resourceInfo.getResourceMethod();

        System.out.println(">>>>>>>>>>>>>>>>>>>>> " + method.getName());

        if (method.isAnnotationPresent(PermitAll.class))
            return;

        if (method.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .entity(new ResponseWrapper(false, "not allowed"))
                .type(MediaType.APPLICATION_JSON).build());
        }

        //GET REQUEST HEADERS
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        List<String> authorization = headers.get("Authorization");

        if (authorization == null || authorization.isEmpty() || authorization.get(0) == null) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .entity(new ResponseWrapper(false, "authorization required"))
                .type(MediaType.APPLICATION_JSON).build());
            return;
        }

        System.out.println("is this bearer of basic " + authorization.get(0));
        String authorizationHeader = authorization.get(0);

        if (authorizationHeader.contains("Basic")) {
            System.out.println(" Will will do basic authentication");
        } else if (authorizationHeader.contains("Bearer")) {
            String bearerToken = authorizationHeader.replace("Bearer", "").trim();
            System.out.println(bearerToken);

            if (!authBean.authMd5(bearerToken)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseWrapper(false, "Invalid authorization"))
                    .type(MediaType.APPLICATION_JSON).build());

                return;
            }

        } else  {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .entity(new ResponseWrapper(false, "only basic or bearer authorization is allowed"))
                .type(MediaType.APPLICATION_JSON).build());

        }

    }
}
