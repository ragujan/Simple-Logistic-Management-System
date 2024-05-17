package com.jiat.ejb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "destination")
public class Destination implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "destination_name")
    private String destinationName;

    @OneToMany(mappedBy = "destinationId")
    private List<Route> routeSet = new ArrayList<>();



    public Destination() {}

    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public List<Route> getRouteSet() {
        return routeSet;
    }

    public void setRouteSet(List<Route> routeSet) {
        this.routeSet = routeSet;
    }

    // toString method
    @Override
    public String toString() {
        return "TransportationType{" +
                "id=" + id +
                ", name='" + destinationName + '\'' +
                '}';
    }
}
