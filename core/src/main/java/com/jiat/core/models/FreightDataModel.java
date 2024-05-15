package com.jiat.core.models;

import java.time.LocalDateTime;

public class FreightDataModel {

    private Integer id;

    private String weight;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
    private Double eta;
    private Integer transportationId;
    private String transportationName;
    private Integer routeId;
    private String routeName;
    private String delivered;
    private String hasJourneyStarted;

    public String getHasJourneyStarted() {
        return hasJourneyStarted;
    }

    public void setHasJourneyStarted(String hasJourneyStarted) {
        this.hasJourneyStarted = hasJourneyStarted;
    }

    public String getDelivered() {
        return delivered;
    }

    public String getFailed() {
        return failed;
    }

    private String failed;
    public String getTransportationName() {
        return transportationName;
    }

    public void setTransportationName(String transportationName) {
        this.transportationName = transportationName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }




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

    public String isDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String isFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }
}
