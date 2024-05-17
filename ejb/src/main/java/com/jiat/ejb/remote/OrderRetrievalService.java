package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Orders;
import jakarta.ejb.Remote;

@Remote
public interface OrderRetrievalService {
    public Orders retrieveOrderById(String id);
}
