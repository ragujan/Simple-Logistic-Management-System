package com.jiat.ejb.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "transportation_type")
public class TransportationType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public TransportationType() {}

    public TransportationType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString method
    @Override
    public String toString() {
        return "TransportationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
