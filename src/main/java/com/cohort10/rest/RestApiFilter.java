package com.cohort10.rest;

import com.cohort10.bean.AuthBeanI;
import com.cohort10.model.Auth;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            String basicToken = authorizationHeader.replace("Basic", "").trim();
            System.out.println(basicToken);

            byte[] decoded = Base64.decodeBase64(basicToken);
            String [] authDecoded = new String(decoded, "UTF-8").split(":");

            Auth auth = new Auth();
            auth.setUsername(authDecoded[0]);
            auth.setPassword(authDecoded[1]);
            try {
                authBean.login(auth);
            } catch (Exception ex2) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseWrapper(false, ex2.getMessage()))
                    .type(MediaType.APPLICATION_JSON).build());
            }

        } else if (authorizationHeader.contains("Bearer")) {
            String bearerToken = authorizationHeader.replace("Bearer", "").trim();
            System.out.println(bearerToken);

            if (!authBean.authMd5(bearerToken)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseWrapper(false, "Invalid authorization"))
                    .type(MediaType.APPLICATION_JSON).build());
            }

            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed roles = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(roles.value()));

                if (!rolesSet.contains("SIMON")) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseWrapper(false, "User profile not allowed"))
                    .type(MediaType.APPLICATION_JSON).build());
                }


            }

        } else  {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .entity(new ResponseWrapper(false, "only basic or bearer authorization is allowed"))
                .type(MediaType.APPLICATION_JSON).build());

        }

    }
}
