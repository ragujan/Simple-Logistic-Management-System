package com.jiat.core.models;

import java.io.Serializable;

public class TransportationDataModel implements Serializable {
    private Integer id;
    private String type;
    private String name;
    private String maximumWeight;

    public TransportationDataModel(Integer id, String type, String name, String maximumWeight) {
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

    public String getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(String maximumWeight) {
        this.maximumWeight = maximumWeight;
    }
}
