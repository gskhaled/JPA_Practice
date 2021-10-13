package com.example.controllers;

import com.example.Application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/query")
public class QueriesResource {
    @GET
    @Path("/1")
    public String doquery1() {
        System.out.println("In query 1");
        return Application.query1().toString();
    }

    @GET
    @Path("/2/{projectName}")
    public String doquery2(@PathParam("projectName") String projectName) {
        System.out.println("In query 2");
        return Application.query2(projectName).toString();
    }

    @GET
    @Path("/3/{employeeId}/{projectId}")
    public String doquery3(@PathParam("employeeId") String employeeId,
                           @PathParam("projectId") String projectId) {
        System.out.println("In query 3");
        return Application.query3(Integer.parseInt(employeeId), Integer.parseInt(projectId));
    }

    @GET
    @Path("/4/{role}")
    public String doquery4(@PathParam("role") String role) {
        System.out.println("In query 4");
//        return "DONE: " + role;
        return Application.query4(role).toString();
    }
}
