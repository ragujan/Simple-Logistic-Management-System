package com.jiat.ejb.entity;


import jakarta.persistence.*;

@Entity
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transportation_type_id", referencedColumnName = "id")
    private TransportationType transportationType;

    public Float getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(Float maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    @Column(name = "maximum_weight")
    private Float maximumWeight;
    private String name;

    // Constructors, getters, and setters
    // Constructors
    public Transportation() {}

    public Transportation(TransportationType transportationType, String name) {
        this.transportationType = transportationType;
        this.name = name;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransportationType getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(TransportationType transportationType) {
        this.transportationType = transportationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString method
    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", transportationType=" + transportationType +
                ", name='" + name + '\'' +
                '}';
    }
}
