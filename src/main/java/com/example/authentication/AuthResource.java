package com.example.authentication;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;



@Path("/auth")
public class AuthResource {

    @Path("/auth")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser( Credentials credentials) {
        try {
            boolean authorizationResult = Authenticator.authenticate(credentials);
            if (authorizationResult) {
                return Response.ok()
                        .header("Authorization", true).build();
            }
                return Response.status(Response.Status.UNAUTHORIZED).header("Authorization", false).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}