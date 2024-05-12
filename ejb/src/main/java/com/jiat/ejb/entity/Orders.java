package com.jiat.ejb.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



//    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors, getters, and setters
    // Constructors
    public Orders() {}

    public Orders( Product productId, Integer qty, LocalDateTime createdAt) {
        this.productId = productId;
        this.qty = qty;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", qty=" + qty +
                ", createdAt=" + createdAt +
                '}';
    }
}