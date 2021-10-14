package com.example.services;

import org.apache.http.HttpEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class QueriesServiceTest {
    @Test
    @DisplayName("Testing query 1")
    void query1Test() {
        WebTarget queries = ClientBuilder.newClient().target("http://localhost:8080/api/query/1");
        Response response = queries.request().get();
        System.out.println(response.readEntity(HttpEntity.class));
//        try {
//            System.out.println(EntityUtils.toString(response.readEntity(HttpEntity.class)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    @DisplayName("Testing query 2")
    void query2Test() {
        WebTarget queries = ClientBuilder.newClient().target("http://localhost:8080/api/query/2/electricity");
        Response response = queries.request().get();
        System.out.println(response.readEntity(String.class));
    }

    @Test
    @DisplayName("Testing query 3")
    void query3Test() {

    }

    @Test
    @DisplayName("Testing query 4")
    void query4Test() {
        WebTarget queries = ClientBuilder.newClient().target("http://localhost:8080/api/query/4/hr");
        Response response = queries.request().get();
        System.out.println(response);
    }

    @Test
    @DisplayName("Testing query 1 paginated")
    void query1PaginatedTest() {

    }
}
