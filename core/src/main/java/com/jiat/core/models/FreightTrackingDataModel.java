package com.jiat.core.models;

public class FreightTrackingDataModel {
    private Integer freightTrackingId;
    private Integer freightId;
    private Integer freightRoute;
    private Integer routeDestination;
    private String freightProgress;
    private String coordinates;
    private String expectedDelay;

    public Integer getFreightTrackingId() {
        return freightTrackingId;
    }

    public void setFreightTrackingId(Integer freightTrackingId) {
        this.freightTrackingId = freightTrackingId;
    }

    public Integer getFreightId() {
        return freightId;
    }

    public void setFreightId(Integer freightId) {
        this.freightId = freightId;
    }

    public Integer getFreightRoute() {
        return freightRoute;
    }

    public void setFreightRoute(Integer freightRoute) {
        this.freightRoute = freightRoute;
    }

    public Integer getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(Integer routeDestination) {
        this.routeDestination = routeDestination;
    }

    public String getFreightProgress() {
        return freightProgress;
    }

    public void setFreightProgress(String freightProgress) {
        this.freightProgress = freightProgress;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getExpectedDelay() {
        return expectedDelay;
    }

    public void setExpectedDelay(String expectedDelay) {
        this.expectedDelay = expectedDelay;
    }
}
