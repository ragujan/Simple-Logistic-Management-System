package com.jiat.core.models;

import java.io.Serializable;

public class TransportationDataModel implements Serializable {
    private Integer id;
    private String type;
    private String name;
    private Float maximumWeight;

    public TransportationDataModel(Integer id, String type, String name, Float maximumWeight) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.maximumWeight = maximumWeight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(Float maximumWeight) {
        this.maximumWeight = maximumWeight;
    }
}
