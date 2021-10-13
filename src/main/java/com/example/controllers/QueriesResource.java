package com.example.controllers;

import com.example.Application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/query")
public class QueriesResource {
    @GET
    @Path("/1")
    public Response doquery1() {
        System.out.println("In query 1");
        return Response.ok(Application.query1JSON()).build();
    }

    @GET
    @Path("/2/{projectName}")
    public Response doquery2(@PathParam("projectName") String projectName) {
        System.out.println("In query 2");
        return Response.ok(Application.query2JSON(projectName)).build();
    }

    @GET
    @Path("/3/{employeeId}/{projectId}")
    public Response doquery3(@PathParam("employeeId") String employeeId,
                             @PathParam("projectId") String projectId) {
        System.out.println("In query 3");
        return Response.ok(
                        Application.query3(Integer.parseInt(employeeId),
                                Integer.parseInt(projectId)))
                .build();
    }

    @GET
    @Path("/4/{role}")
    public Response doquery4(@PathParam("role") String role) {
        System.out.println("In query 4");
        return Response.ok(Application.query4JSON(role)).build();
    }
}
