package com.example.tables;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "age", nullable = false)
    private short age;

    @Column(name = "nationalID", nullable = false, length = 14)
    private String nationalID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rolee")
    private Role rolee;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employeeprojects",
            joinColumns = @JoinColumn(name = "project"),
            inverseJoinColumns = @JoinColumn(name = "employee")
    )
    private Set<Project> employeeProjects = new HashSet<Project>();

    public Role getRole() {
        return rolee;
    }

    public void setRole(Role role) {
        this.rolee = role;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Project> getEmployeeProjects() {
        return employeeProjects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", nationalID='" + nationalID + '\'' +
                ", rolee=" + rolee +
                ", employeeProjects=" + employeeProjects +
                '}';
    }
}