package com.example.controllers;

import com.example.services.QueryService;
import com.example.services.QueryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/query")
public class QueriesController {
    QueryService queryService = new QueryServiceImpl();

    @GET
    @Path("/1")
    public Response doquery1() {
        System.out.println("In query 1");
        return Response.ok(queryService.query1JSON()).build();
    }

    @GET
    @Path("/2/{projectName}")
    public Response doquery2(@PathParam("projectName") String projectName) {
        System.out.println("In query 2");
        return Response.ok(queryService.query2JSON(projectName)).build();
    }

    @GET
    @Path("/3/{employeeId}/{projectId}")
    public Response doquery3(@PathParam("employeeId") String employeeId,
                             @PathParam("projectId") String projectId) {
        System.out.println("In query 3");
        return Response.ok(
                        queryService.query3(Integer.parseInt(employeeId),
                                Integer.parseInt(projectId)))
                .build();
    }

    @GET
    @Path("/4/{role}")
    public Response doquery4(@PathParam("role") String role) {
        System.out.println("In query 4");
        return Response.ok(queryService.query4JSON(role)).build();
    }

    @POST
    @Path("/page")
    @Consumes(MediaType.APPLICATION_JSON)
    public String doquery1Paginated(String data) {
        return queryService.query1Paginated(data);
    }
}
