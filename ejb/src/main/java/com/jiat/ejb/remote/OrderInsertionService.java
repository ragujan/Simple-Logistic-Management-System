package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface OrderInsertionService {
    public boolean createOrder(String destinationId,String merchant, String product, String qty,String expectedDate);
}
