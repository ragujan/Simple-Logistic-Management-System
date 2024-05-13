package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface OrderService {
    public boolean createOrder(String merchant, String product, String qty);
}
