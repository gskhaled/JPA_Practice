package com.example;

import com.example.tables.Employee;
import com.example.tables.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public static List<Project> query2() {
        EntityManager em = getEntityManager();
        List<Project> employees = (List<Project>) em.createQuery("select p from Project p where p.name = 'electricity'").getResultList();
        System.out.println("Executing query 2...........");
        employees.forEach(
                System.out::println
        );
        return employees;
    }

//    public static List<Project> query3() {
//        EntityManager em = getEntityManager();
//        List<Project> employees = (List<Project>) em.createQuery(" ").getResultList();
//        System.out.println("Executing query 3...........");
//        employees.forEach(
//                System.out::println
//        );
//        return employees;
//    }

    public static List<Employee> query4() {
        EntityManager em = getEntityManager();
        List<Employee> employees = (List<Employee>) em.createQuery("select e from Employee e where e.rolee = 'hr' and e.employeeProjects.size = 0").getResultList();
        System.out.println("Executing query 4...........");
        employees.forEach(
                System.out::println
        );
        return employees;
    }

    public static void main(String[] args) {
        query2();
//        getEntityManager();
    }
}
