package com.example.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "roles")
@Entity
public class Role {
    @Id
    @Column(name = "name", length = 20)
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
