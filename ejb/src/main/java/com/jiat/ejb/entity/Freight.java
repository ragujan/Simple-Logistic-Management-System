package com.jiat.ejb.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "freight")
public class Freight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "weight")
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "transportation_id")
    private Transportation transportation;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "eta")
    private Double eta;

    @Column(name = "is_delivered")
    private boolean delivered;

    @Column(name = "is_failed")
    private boolean failed;
    @Column(name = "has_started")
    private boolean hasStarted;


    @OneToMany(mappedBy = "freight")
    private List<FreightTracking> freightTrackings;

    @OneToMany(mappedBy = "freight")
    private List<FreightHasOrders> freightHasOrders;


    public List<FreightTracking> getFreightTrackings() {
        return freightTrackings;
    }

    public void setFreightTrackings(List<FreightTracking> freightTrackings) {
        this.freightTrackings = freightTrackings;
    }

    public List<FreightHasOrders> getFreightHasOrders() {
        return freightHasOrders;
    }

    public void setFreightHasOrders(List<FreightHasOrders> freightHasOrders) {
        this.freightHasOrders = freightHasOrders;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }
// Constructors, getters, and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getEta() {
        return eta;
    }

    public void setEta(Double eta) {
        this.eta = eta;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }
}