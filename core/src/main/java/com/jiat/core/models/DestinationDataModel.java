package com.jiat.core.models;

import java.time.LocalDateTime;

public class DestinationDataModel {
    private String freightId;
    private String destination;
    private LocalDateTime startingDate;

    private String destinationWithStartingDate;

    public DestinationDataModel(String destination, LocalDateTime startingDate, String freightId) {
        this.destination = destination;
        this.startingDate = startingDate;
        this.freightId = freightId;
    }

    public String getDestinationWithStartingDate() {
        this.destinationWithStartingDate = destination + "--"+startingDate;
        return this.destinationWithStartingDate;
    }

    public String getFreightId() {
        return freightId;
    }

    public void setFreightId(String freightId) {
        this.freightId = freightId;
    }

    public void setDestinationWithStartingDate(String destinationWithStartingDate) {
        this.destinationWithStartingDate = destinationWithStartingDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }
}
