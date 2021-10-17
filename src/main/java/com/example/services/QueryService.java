package com.example.services;

import javax.persistence.EntityManager;

public interface QueryService {
    EntityManager getEntityManager();

    String query1JSON();

    String query2JSON(String projectName);

    String query3(int employeeId, int projectId);

    String query4JSON(String role);

    String query1Paginated(String data);
}
