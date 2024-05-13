package com.jiat.ejb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "starting_point")
    private String startingPoint;

    @Column(name = "destination_point")
    private String destinationPoint;

    private String name;

    public Route() {}

    public Route(String startingPoint, String destinationPoint, String name) {
        this.startingPoint = startingPoint;
        this.destinationPoint = destinationPoint;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
