package com.jiat.ejb.remote;

public interface OrderService {
    public boolean createOrder(String merchant, String product, String qty);
}
