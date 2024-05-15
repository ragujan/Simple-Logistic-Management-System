package com.jiat.core.models;

import java.time.LocalDateTime;

public class FreightDataModel {

    private Integer id;

    private String weight;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
    private Double eta;
    private Integer transportationId;



    private Integer routeId;




    private boolean delivered;

    private boolean failed;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getTransportationId() {
        return transportationId;
    }

    public void setTransportationId(Integer transportationId) {
        this.transportationId = transportationId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
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
