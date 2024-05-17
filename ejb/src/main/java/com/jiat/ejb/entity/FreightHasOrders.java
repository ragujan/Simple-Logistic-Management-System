package com.jiat.ejb.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "freight_has_orders")
public class FreightHasOrders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "freight_id")
    private Freight freight;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Freight getFreight() {
        return freight;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
