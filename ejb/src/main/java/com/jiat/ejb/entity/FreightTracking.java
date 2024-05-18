package com.jiat.ejb.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "freight_tracking")
public class FreightTracking implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "coordinates")
    private String coordinates;

    @Column (name = "route_progress")
    private String routeProgress;

    @Column(name = "expected_delay")
    private String expectedDelay;


    @ManyToOne
    @JoinColumn(name = "freight_id")
    private Freight freight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getRouteProgress() {
        return routeProgress;
    }

    public void setRouteProgress(String routeProgress) {
        this.routeProgress = routeProgress;
    }

    public String getExpectedDelay() {
        return expectedDelay;
    }

    public void setExpectedDelay(String expectedDelay) {
        this.expectedDelay = expectedDelay;
    }

    public Freight getFreight() {
        return freight;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }
}
