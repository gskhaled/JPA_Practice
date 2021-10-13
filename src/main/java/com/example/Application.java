package com.example;

import com.example.tables.Employee;
import com.example.tables.Project;
import com.example.tables.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@PersistenceContext
public class Application {
    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        return emf.createEntityManager();
    }

    public static List<Employee> query1() {
        EntityManager em = getEntityManager();
        List<Employee> employees = (List<Employee>) em.createQuery("SELECT e FROM Employee e").getResultList();
        System.out.println("Executing query 1...........");
        employees.forEach(
                System.out::println
        );
        return employees;
    }

    public static String query1JSON() {
        EntityManager em = getEntityManager();
        List<Employee> employees = (List<Employee>) em.createQuery("SELECT e FROM Employee e").getResultList();
        System.out.println("Executing query 1...........");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    public static Set<Employee> query2(String projectName) {
        try {
            EntityManager em = getEntityManager();
            Project project = (Project) em.createQuery("select p from Project p where p.name = ?1")
                    .setParameter(1, projectName)
                    .getSingleResult();
            System.out.println("Executing query 2...........");
            Set<Employee> employees = project.getEmployeeProjects();
            for (Employee e : employees) {
                System.out.println("Employee: " + e.getName() + " has email: " + e.getEmail() + " and ID: " + e.getId());
            }
            return employees;
        } catch (Exception e) {
            System.out.println("Error fetching employees");
            return null;
        }
    }

    public static String query2JSON(String projectName) {
        String jsonString = "";
        try {
            EntityManager em = getEntityManager();
            Project project = (Project) em.createQuery("select p from Project p where p.name = ?1")
                    .setParameter(1, projectName)
                    .getSingleResult();
            System.out.println("Executing query 2...........");
            Set<Employee> employees = project.getEmployeeProjects();
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (Exception e) {
            System.out.println("Error fetching employees");
        }
        return jsonString;
    }

    public static String query3(int employeeId, int projectId) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Employee employee = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1")
                    .setParameter(1, employeeId)
                    .getSingleResult();
            Project project = (Project) em.createQuery("SELECT p FROM Project p WHERE p.id = ?1")
                    .setParameter(1, projectId)
                    .getSingleResult();
            employee.getEmployeeProjects().add(project);
            em.persist(employee);
            em.getTransaction().commit();
            System.out.println("Executing query 3...........");
            System.out.println("Successfully added ProjectID to Employee");
            return "Successfully added ProjectID to Employee";
        } catch (Exception e) {
            System.out.println("Failed to add Project to Employee");
            return "Failed to add Project to Employee";
        }
    }

    public static List<Employee> query4(String role) {
        EntityManager em = getEntityManager();
        Role rolee = new Role(role);
        List<Employee> employees = (List<Employee>) em.createQuery("select e from Employee e where e.rolee = ?1 and e.employeeProjects.size = 0")
                .setParameter(1, rolee)
                .getResultList();
        System.out.println("Executing query 4...........");
        employees.forEach(
                System.out::println
        );
        return employees;
    }

    public static String query4JSON(String role) {
        EntityManager em = getEntityManager();
        Role rolee = new Role(role);
        List<Employee> employees = (List<Employee>) em.createQuery("select e from Employee e where e.rolee = ?1 and e.employeeProjects.size = 0")
                .setParameter(1, rolee)
                .getResultList();
        System.out.println("Executing query 4...........");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    public static void main(String[] args) {
//        query2JSON("electricity");
//        query4JSON("hr");
    }
}
