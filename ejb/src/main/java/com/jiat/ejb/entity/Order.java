package com.jiat.ejb.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchantId;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product productId;

    private Integer qty;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors, getters, and setters
    // Constructors
    public Order() {}

    public Order(Merchant merchantId, Product productId, Integer qty, LocalDateTime createdAt) {
        this.merchantId = merchantId;
        this.productId = productId;
        this.qty = qty;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Merchant getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Merchant merchantId) {
        this.merchantId = merchantId;
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
                ", merchant=" + merchantId +
                ", productId=" + productId +
                ", qty=" + qty +
                ", createdAt=" + createdAt +
                '}';
    }
}