package com.jiat.ejb.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "route")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "starting_point")
    private String startingPoint;

    @Column(name = "destination_point")
    private String destinationPoint;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destinationId;

    @Column(name = "route_order")
    private Integer routeOrder;
    private String name;

    public Route() {
    }

    public Route(Destination destination, String startingPoint, String destinationPoint, String name) {
        this.destinationId = destination;
        this.startingPoint = startingPoint;
        this.destinationPoint = destinationPoint;
        this.name = name;
    }

    public Integer getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(Integer routeOrder) {
        this.routeOrder = routeOrder;
    }

    public Destination getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Destination destinationId) {
        this.destinationId = destinationId;
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
