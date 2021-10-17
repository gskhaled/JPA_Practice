package com.example.services;

import com.example.tables.Employee;
import com.example.tables.Project;
import com.example.tables.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class QueryServiceImpl implements QueryService {
    public EntityManager em = getEntityManager();

    public static void main(String[] args) {
//        query1Paginated(String.format("{\"pageIndex\":\"%s\",\"pageSize\":\"%s\"}",
//                55, 1));
//        new QueryServiceImpl().query1JSON();
        new QueryServiceImpl().query4JSON("hr");
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        return emf.createEntityManager();
    }

//    public static List<Employee> query1() {
//        QueryServiceImpl queryService = new QueryServiceImpl();
//        EntityManager em = queryService.getEntityManager();
//        List<Employee> employees = (List<Employee>) em.createQuery("SELECT e FROM Employee e").getResultList();
//        System.out.println("Executing query 1...........");
//        employees.forEach(
//                System.out::println
//        );
//        return employees;
//    }

//    public static Set<Employee> query2(String projectName) {
//        try {
//            QueryServiceImpl queryService = new QueryServiceImpl();
//            EntityManager em = queryService.getEntityManager();
//            Project project = (Project) em.createQuery("select p from Project p where p.name = ?1")
//                    .setParameter(1, projectName)
//                    .getSingleResult();
//            System.out.println("Executing query 2...........");
//            Set<Employee> employees = project.getEmployeeProjects();
//            for (Employee e : employees) {
//                System.out.println("Employee: " + e.getName() + " has email: " + e.getEmail() + " and ID: " + e.getId());
//            }
//            return employees;
//        } catch (Exception e) {
//            System.out.println("Error fetching employees");
//            return null;
//        }
//    }

    public String query1JSON() {
//        EntityManager em = getEntityManager();
        List<Employee> employees = (List<Employee>) em.createQuery("SELECT e FROM Employee e").getResultList();
        System.out.println("Executing query 1...........");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.findAndRegisterModules();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    public String query2JSON(String projectName) {
        String jsonString = "";
        try {
            Project project = (Project) em.createQuery("select p from Project p where p.name = ?1")
                    .setParameter(1, projectName)
                    .getSingleResult();
            System.out.println("Executing query 2...........");
            Set<Employee> employees = project.getEmployeeProjects();
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching employees");
        }
        return jsonString;
    }

//    public static List<Employee> query4(String role) {
//        QueryServiceImpl queryService = new QueryServiceImpl();
//        EntityManager em = queryService.getEntityManager();
//        Role rolee = new Role(role);
//        List<Employee> employees = (List<Employee>) em.createQuery("select e from Employee e where e.rolee = ?1 and e.employeeProjects.size = 0")
//                .setParameter(1, rolee)
//                .getResultList();
//        System.out.println("Executing query 4...........");
//        employees.forEach(
//                System.out::println
//        );
//        return employees;
//    }

    public String query3(int employeeId, int projectId) {
        try {
            em.getTransaction().begin();
            Project project = (Project) em.createQuery("SELECT p FROM Project p WHERE p.id = ?1")
                    .setParameter(1, projectId)
                    .getSingleResult();
            Employee employee = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1")
                    .setParameter(1, employeeId)
                    .getSingleResult();
            employee.getEmployeeProjects().add(project);
            em.persist(employee);
            em.getTransaction().commit();
            System.out.println("Executing query 3...........");
            System.out.println("Successfully added ProjectID to Employee");
            return "Successfully added ProjectID to Employee";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to add Project to Employee");
            return "Failed to add Project to Employee";
        }
    }

    public String query4JSON(String role) {
        Role rolee = new Role(role);
        List<Employee> employees = (List<Employee>) em.createQuery("select e from Employee e where e.rolee = ?1 and e.employeeProjects.size = 0")
                .setParameter(1, rolee)
                .getResultList();
        System.out.println("Executing query 4...........");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.findAndRegisterModules();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    public String query1Paginated(String data) {
        JSONObject json = new JSONObject(data);
        int pageIndex = json.getInt("pageIndex");
        int pageSize = json.getInt("pageSize");

        System.out.println("Executing query 1...........");
        Query query = em.createQuery("SELECT e FROM Employee e");
        query.setFirstResult((pageIndex - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Employee> employees = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.findAndRegisterModules();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }
}
