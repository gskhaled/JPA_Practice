package com.example.tables;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_manager", referencedColumnName = "id")
    private Employee projectManager;

    public Set<Employee> getEmployeeProjects() {
        return employeeProjects;
    }

    public void setEmployeeProjects(Set<Employee> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    @ManyToMany(mappedBy = "employeeProjects", cascade = CascadeType.ALL)
    private Set<Employee> employeeProjects = new HashSet<Employee>();

    public void setProjectManager(Employee projectManager) {
        this.projectManager = projectManager;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", name='" + name + '\'' +
                ", projectManager=" + projectManager.getName() +
                ", employeeProjects size=" + employeeProjects.size() +
                '}';
    }
}